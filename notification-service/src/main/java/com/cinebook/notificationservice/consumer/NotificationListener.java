package com.cinebook.notificationservice.consumer;

import com.cinebook.notificationservice.config.RabbitMQConfig;
import com.cinebook.notificationservice.dto.BookingMessage;
import com.cinebook.notificationservice.service.EmailService;
import com.cinebook.notificationservice.util.TicketGenerator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class NotificationListener {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TicketGenerator ticketGenerator;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consumeMessageFromQueue(BookingMessage message) {
        System.out.println("🐇 Received Message: " + message);

        try {
            // 1. Generate PDF Ticket (Saves file to disk)
            // Note: If you updated TicketGenerator to accept Date/Amount, update the arguments below.
            // Using default args based on your previous snippet:
            ticketGenerator.generateTicket(
                message.getBookingId(), 
                message.getMovieName(), 
                message.getSeatNumber(),
                "Upcoming Show", // Default if date is missing in DTO
                "Paid"           // Default if amount is missing in DTO
            );

            // 2. Locate the Generated File
            String fileName = "Ticket_Booking_" + message.getBookingId() + ".pdf";
            File ticketFile = new File(fileName);

            // 3. Send Email with Attachment
            if (message.getEmail() != null && !message.getEmail().isEmpty()) {
                
                // Create a nice HTML body
                String htmlBody = buildEmailTemplate(message);

                // Use the new method in EmailService that handles attachments
                emailService.sendEmailWithAttachment(
                    message.getEmail(),
                    "CineBook Booking Confirmed: " + message.getMovieName(),
                    htmlBody,
                    ticketFile
                );

            } else {
                System.out.println("⚠️ No email provided in booking message. Skipping email.");
            }

            // 4. Cleanup: Delete the temporary PDF file
            if (ticketFile.exists()) {
                ticketFile.delete();
                System.out.println("🗑️ Temporary ticket file deleted: " + fileName);
            }

        } catch (Exception e) {
            System.err.println("❌ Error processing notification: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to create a professional HTML email body
    private String buildEmailTemplate(BookingMessage msg) {
        return "<html>" +
                "<body style='font-family: Arial, sans-serif; color: #333;'>" +
                "  <div style='background-color: #E50914; padding: 20px; text-align: center; color: white;'>" +
                "    <h1>Booking Confirmed! 🍿</h1>" +
                "  </div>" +
                "  <div style='padding: 20px; border: 1px solid #ddd;'>" +
                "    <p>Hello,</p>" +
                "    <p>Your tickets for <b>" + msg.getMovieName() + "</b> are confirmed.</p>" +
                "    <p><b>Seats:</b> " + msg.getSeatNumber() + "</p>" +
                "    <p><b>Booking ID:</b> #" + msg.getBookingId() + "</p>" +
                "    <br>" +
                "    <p>Please find your official <b>E-Ticket attached</b> to this email.</p>" +
                "    <p>Scan the QR code at the cinema entrance.</p>" +
                "    <br>" +
                "    <a href='http://localhost:8082/tickets.html' style='background-color: #E50914; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;'>View My Bookings</a>" +
                "  </div>" +
                "  <div style='margin-top: 20px; font-size: 12px; color: #777; text-align: center;'>" +
                "    &copy; 2025 CineBook. All rights reserved." +
                "  </div>" +
                "</body>" +
                "</html>";
    }
}