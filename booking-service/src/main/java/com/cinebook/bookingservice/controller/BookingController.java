package com.cinebook.bookingservice.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cinebook.bookingservice.client.MovieClient;
import com.cinebook.bookingservice.config.RabbitMQConfig;
import com.cinebook.bookingservice.dto.BookingMessage;
import com.cinebook.bookingservice.entity.Booking;
import com.cinebook.bookingservice.repository.BookingRepository;
import com.cinebook.bookingservice.util.TicketGenerator;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private MovieClient movieClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private TicketGenerator ticketGenerator;

    // --- UPDATED: DOWNLOAD PDF TICKET ---
    @GetMapping("/download/{bookingId}")
    public ResponseEntity<byte[]> downloadTicket(@PathVariable Long bookingId) {
        Booking b = bookingRepo.findById(bookingId).orElse(null);
        
        if (b == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String movieName = "Movie #" + b.getMovieId(); 
        String seatInfo = "Seat " + b.getSeatId();
        String date = b.getBookingTime().toLocalDate().toString(); 
        
        // UPDATED: Now uses the real amount stored in the database
        String amount = b.getAmount() != null ? b.getAmount().toString() : "0.00"; 

        byte[] pdfContent = ticketGenerator.generateTicket(bookingId, movieName, seatInfo, date, amount);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "CineBook_Ticket_" + bookingId + ".pdf");

        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }

    // --- EXISTING ENDPOINTS ---

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable String userId) {
        List<Booking> userBookings = bookingRepo.findByUserId(userId);
        return ResponseEntity.ok(userBookings);
    }

    // --- UPDATED: CREATE BOOKING (Saves Amount for Admin Dashboard) ---
    @GetMapping("/create")
    @CircuitBreaker(name = "movieService", fallbackMethod = "bookingFallback")
    public String createBooking(
            @RequestParam String userId, 
            @RequestParam Long seatId, 
            @RequestParam Long movieId,
            @RequestParam String email,
            @RequestParam String movieTitle,
            @RequestParam(defaultValue = "450.0") Double price) { // ADDED: Price parameter
        
        System.out.println("DEBUG: Processing Booking for: " + movieTitle + " (User: " + userId + ")");

        boolean isEvent = (movieId == 0);
        boolean success = false;

        if (isEvent) {
            success = true; 
        } else {
            success = movieClient.bookSeat(seatId, 1L); 
        }

        if (success) {
            Booking b = new Booking();
            b.setUserId(userId);
            b.setSeatId(seatId);
            b.setMovieId(movieId);
            b.setBookingTime(LocalDateTime.now());
            
            // UPDATED: Saving the amount so the Admin Dashboard can calculate revenue
            b.setAmount(price); 
            
            bookingRepo.save(b);

            try {
                String seatInfo = isEvent ? "General Admission" : "Seat ID: " + seatId;

                BookingMessage message = new BookingMessage(
                    b.getId(), 
                    movieTitle, 
                    seatInfo, 
                    "CONFIRMED", 
                    email
                );
                
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, message);
                return "Success";
            } catch (Exception e) {
                e.printStackTrace();
                return "Booking Saved, Notification Failed.";
            }
        }
        return "Failed";
    }

    public String bookingFallback(String userId, Long seatId, Long movieId, String email, String movieTitle, Double price, Throwable t) {
        System.err.println("Circuit Breaker Triggered: " + t.getMessage());
        return "⚠️ Service Unavailable. Please try again later.";
    }
}