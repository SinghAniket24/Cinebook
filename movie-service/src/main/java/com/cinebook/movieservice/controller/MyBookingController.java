package com.cinebook.movieservice.controller;

import com.cinebook.movieservice.entity.Movie;
import com.cinebook.movieservice.entity.Seat;
import com.cinebook.movieservice.repository.MovieRepository;
import com.cinebook.movieservice.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/bookings")
public class MyBookingController {

    @Autowired private SeatRepository seatRepo;
    @Autowired private MovieRepository movieRepo;

    @GetMapping("/{userId}")
    public List<Map<String, Object>> getUserBookings(@PathVariable Long userId) {
        // 1. Find all seats booked by this user
        List<Seat> userSeats = seatRepo.findAll().stream()
                .filter(s -> userId.equals(s.getUserId()) && "BOOKED".equals(s.getStatus()))
                .collect(Collectors.toList());

        // 2. Group them by Movie
        List<Map<String, Object>> history = new ArrayList<>();

        Map<Long, List<Seat>> seatsByMovie = userSeats.stream()
                .collect(Collectors.groupingBy(Seat::getMovieId));

        for (Map.Entry<Long, List<Seat>> entry : seatsByMovie.entrySet()) {
            Long movieId = entry.getKey();
            List<Seat> seats = entry.getValue();

            Movie movie = movieRepo.findById(movieId).orElse(new Movie());
            
            // Generate a simpler string for seat numbers (e.g., "A1, A2")
            String seatNumbers = seats.stream()
                                      .map(Seat::getSeatNumber)
                                      .collect(Collectors.joining(", "));
            
            double totalAmount = seats.stream().mapToDouble(Seat::getPrice).sum();

            history.add(Map.of(
                "movieTitle", movie.getTitle() != null ? movie.getTitle() : "Unknown Movie",
                "seatNumbers", seatNumbers,
                "totalAmount", totalAmount,
                "ticketCount", seats.size(),
                "status", "CONFIRMED",
                "bookingId", "BK-" + seats.get(0).getId() + "-" + userId // Mock Booking ID
            ));
        }

        return history;
    }
}