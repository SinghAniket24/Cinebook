package com.cinebook.bookingservice.controller;

import java.time.LocalDateTime;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cinebook.bookingservice.client.MovieClient;
import com.cinebook.bookingservice.config.RabbitMQConfig;
import com.cinebook.bookingservice.dto.BookingMessage;
import com.cinebook.bookingservice.entity.Booking;
import com.cinebook.bookingservice.repository.BookingRepository;
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

    @GetMapping("/create")
    @CircuitBreaker(name = "movieService", fallbackMethod = "bookingFallback")
    public String createBooking(
            @RequestParam Long userId, 
            @RequestParam Long seatId, 
            @RequestParam Long movieId,
            @RequestParam String email,
            @RequestParam String movieTitle) { // <--- NEW PARAMETER
        
        System.out.println("DEBUG: Booking Title: " + movieTitle);

        boolean success = movieClient.bookSeat(seatId, userId);

        if (success) {
            Booking b = new Booking();
            b.setUserId(userId);
            b.setSeatId(seatId);
            b.setMovieId(movieId);
            b.setBookingTime(LocalDateTime.now());
            bookingRepo.save(b);

            try {
                // Use the REAL title in the message
                BookingMessage message = new BookingMessage(
                    b.getId(), 
                    movieTitle, // <--- PASS IT HERE
                    "A" + seatId, 
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

    public String bookingFallback(Long userId, Long seatId, Long movieId, String email, String movieTitle, Throwable t) {
        return "⚠️ Service Unavailable";
    }
}