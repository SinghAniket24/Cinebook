package com.cinebook.movieservice.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    // New Field for the UI
    private String iconUrl; 

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonIgnore // Prevents the "Infinite Recursion" crash we fixed earlier
    private List<Cinema> cinemas;

    public City() {}

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }

    public List<Cinema> getCinemas() { return cinemas; }
    public void setCinemas(List<Cinema> cinemas) { this.cinemas = cinemas; }
}