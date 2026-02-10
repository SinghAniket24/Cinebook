package com.cinebook.movieservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinebook.movieservice.entity.Movie;
import com.cinebook.movieservice.entity.Seat;
import com.cinebook.movieservice.repository.MovieRepository;
import com.cinebook.movieservice.repository.SeatRepository;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private SeatRepository seatRepo;

    // --- 1. ENTRY POINT (Synchronized) ---
    public synchronized void ensureMovieExists(Long tmdbId, String title) {
        // Fast Check: If seats exist, we are good.
        if (seatRepo.countByMovieId(tmdbId) > 0) return;

        // If not, generate them safely
        generateLayoutSafe(tmdbId, title);
    }

    // --- 2. ROBUST LAYOUT GENERATOR (No Global Transaction) ---
    // We remove @Transactional here so failures in one row don't kill the whole process
    public void generateLayoutSafe(Long tmdbId, String title) {
        System.out.println("✨ Generating Theater Layout for: " + title);

        // A. Save Movie Metadata
        try {
            if (!movieRepo.existsById(tmdbId)) {
                Movie m = new Movie();
                m.setId(tmdbId);
                m.setTitle(title);
                m.setDescription("Imported from TMDB");
                movieRepo.save(m);
            }
        } catch (Exception e) {
            System.out.println("   -> Movie exists (Concurrency handled).");
        }

        // B. Generate Seats (Row by Row)
        // Recliners (Top)
        createRowSafe(tmdbId, "L", 14, 570.0);
        createRowSafe(tmdbId, "K", 14, 570.0);

        // Prime (Middle)
        char[] primeRows = {'J', 'H', 'G', 'F', 'E', 'D', 'C'};
        for (char r : primeRows) createRowSafe(tmdbId, String.valueOf(r), 15, 310.0);

        // Classic (Front)
        createRowSafe(tmdbId, "B", 15, 290.0);
        createRowSafe(tmdbId, "A", 15, 290.0);

        System.out.println("✅ Layout Generation Complete.");
    }

    // Helper: Save individual seat safely
    private void createRowSafe(Long movieId, String rowCode, int seatCount, double price) {
        for (int i = 1; i <= seatCount; i++) {
            try {
                String seatNum = rowCode + i;
                Seat s = new Seat();
                s.setSeatNumber(seatNum);
                s.setMovieId(movieId);
                s.setStatus("AVAILABLE");
                s.setPrice(price);
                seatRepo.save(s); 
            } catch (Exception e) {
                // Ignore duplicates, keep going!
            }
        }
    }

    // --- 3. GETTERS ---
    @Cacheable(value = "movies", key = "#movieId")
    public List<Seat> getSeats(Long movieId) {
        return seatRepo.findByMovieId(movieId);
    }

    // --- 4. BOOKING LOGIC ---

    // NEW: Book Multiple Seats (Now accepts User ID)
    @Transactional
    public boolean bookSeats(List<Long> seatIds, Long userId) {
        List<Seat> seats = seatRepo.findAllById(seatIds);
        
        // Validation: Ensure all seats are available
        for (Seat seat : seats) {
            if (!"AVAILABLE".equals(seat.getStatus())) return false;
        }

        // Processing: Lock seats and assign User ID
        for (Seat seat : seats) {
            seat.setStatus("BOOKED");
            seat.setUserId(userId); // <--- Now works because userId is passed in arguments
            seatRepo.save(seat);
        }
        return true;
    }

    // OLD: Single Seat Wrapper (Passes null for legacy calls)
    @Transactional
    public boolean bookSeat(Long seatId) {
        return bookSeats(List.of(seatId), null);
    }
}