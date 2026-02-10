package com.cinebook.movieservice.repository;

import com.cinebook.movieservice.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    List<Cinema> findByCityId(Long cityId);
    
    // This is REQUIRED for the new data loader
    long countByName(String name);
}