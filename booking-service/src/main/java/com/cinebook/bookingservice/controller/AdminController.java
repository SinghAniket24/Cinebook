package com.cinebook.bookingservice.controller;

import com.cinebook.bookingservice.entity.Booking;
import com.cinebook.bookingservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookings/admin")
@CrossOrigin(origins = "*") 
public class AdminController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/stats")
    public Map<String, Object> getQuickStats() {
        Map<String, Object> stats = new HashMap<>();
        
        Double revenue = bookingRepository.getTotalRevenue();
        Long tickets = bookingRepository.countTotalTickets();

        stats.put("totalRevenue", revenue != null ? revenue : 0.0);
        stats.put("totalTickets", tickets != null ? tickets : 0);
        stats.put("activeTheaters", 5); 
        
        return stats;
    }

    @GetMapping("/revenue-data")
    public List<Double> getWeeklyRevenue() {
        return Arrays.asList(12000.0, 15000.0, 9000.0, 22000.0, 45000.0, 52000.0, 48000.0);
    }
    
    // UPDATED: Changed mapping to /all to match your admin.html fetch request
    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        // This will fetch all records from your MariaDB 'bookings' table
        return bookingRepository.findAll(); 
    }

    // Optional: Keep /recent if you want to use it for a smaller "Latest 5" list later
    @GetMapping("/recent")
    public List<Booking> getRecentBookings() {
        return bookingRepository.findTop10ByOrderByIdDesc();
    }
 // Add these to your AdminController.java

    @GetMapping("/city-stats")
    public List<Map<String, Object>> getCityStats() {
        // In a real app, you'd use a JOIN query between bookings, cinema, and city
        // For now, we'll return structured data based on your MariaDB rows
        return Arrays.asList(
            Map.of("city", "Mumbai", "revenue", 1800.0, "icon", "Gateway of India"),
            Map.of("city", "Delhi-NCR", "revenue", 450.0, "icon", "India Gate"),
            Map.of("city", "Bengaluru", "revenue", 450.0, "icon", "Vidhana Soudha")
        );
    }
}