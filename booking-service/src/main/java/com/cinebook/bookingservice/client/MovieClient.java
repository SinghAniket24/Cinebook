package com.cinebook.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "MOVIE-SERVICE")
public interface MovieClient {

    // 1. The REAL endpoint in MovieController
    // It expects a LIST of IDs and a User ID
    @PostMapping("/movies/seats/book-multiple")
    boolean bookSeats(@RequestBody List<Long> seatIds, @RequestParam("userId") Long userId);

    // 2. The ADAPTER method (Bridge)
    // This allows your BookingController to call 'bookSeat(id, userId)'
    // It automatically wraps the single ID into a List for the backend.
    default boolean bookSeat(Long seatId, Long userId) {
        return bookSeats(List.of(seatId), userId);
    }
}