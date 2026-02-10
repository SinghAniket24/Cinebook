package com.cinebook.bookingservice.dto;

public class BookingMessage {
    private Long bookingId;
    private String movieName;
    private String seatNumber;
    private String status;
    private String email; 

    // Default Constructor
    public BookingMessage() {
    }

    // --- FIXED CONSTRUCTOR ---
    // Added 'String email' to the parameters so it can be assigned correctly
    public BookingMessage(Long bookingId, String movieName, String seatNumber, String status, String email) {
        this.bookingId = bookingId;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.status = status;
        this.email = email; 
    }

    // --- GETTERS ---
    public Long getBookingId() {
        return bookingId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getStatus() {
        return status;
    }
    
    public String getEmail() {
        return email;
    }

    // --- SETTERS ---
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // --- UPDATED TOSTRING ---
    @Override
    public String toString() {
        return "BookingMessage [bookingId=" + bookingId + 
               ", movieName=" + movieName + 
               ", seatNumber=" + seatNumber + 
               ", status=" + status + 
               ", email=" + email + "]";
    }
}