package com.cinebook.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public void sendBookingConfirmation(String toEmail, String movie, String seat, String bookingId) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmail);
            message.setTo(toEmail);
            message.setSubject("CineBook Ticket Confirmed: " + movie);
            message.setText("Hello!\n\n" +
                    "Your ticket for '" + movie + "' has been confirmed.\n" +
                    "Seat Number: " + seat + "\n" +
                    "Booking ID: " + bookingId + "\n\n" +
                    "Show this email at the counter.\n\n" +
                    "Enjoy the show,\nCineBook Team");

            mailSender.send(message);
            System.out.println("📧 Email Sent Successfully to: " + toEmail);
        } catch (Exception e) {
            System.err.println("⚠️ Failed to send email: " + e.getMessage());
        }
    }
}