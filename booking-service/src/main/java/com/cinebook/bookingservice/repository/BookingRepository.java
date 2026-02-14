package com.cinebook.bookingservice.repository;

import com.cinebook.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    // 1. Used for User Profile History
    List<Booking> findByUserId(String userId);
    
    // 2. Used for the "Recent Activity" snippet (Latest 10)
    List<Booking> findTop10ByOrderByIdDesc(); 

    // 3. NEW: Used for the full Admin Table (Latest first)
    // This ensures your table on the admin page stays organized
    List<Booking> findAllByOrderByIdDesc();

    // 4. Admin Stats: Sum of 'amount' column
    @Query("SELECT COALESCE(SUM(b.amount), 0.0) FROM Booking b")
    Double getTotalRevenue();

    // 5. Admin Stats: Total count of rows
    @Query("SELECT COUNT(b) FROM Booking b")
    Long countTotalTickets();
}