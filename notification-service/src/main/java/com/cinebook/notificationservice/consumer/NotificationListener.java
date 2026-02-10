package com.cinebook.notificationservice.consumer;

import com.cinebook.notificationservice.config.RabbitMQConfig; // Ensure this import exists
import com.cinebook.notificationservice.dto.BookingMessage;
import com.cinebook.notificationservice.service.EmailService;
import com.cinebook.notificationservice.util.TicketGenerator; // Keep this if you still want PDF generation
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @Autowired
    private EmailService emailService; // <--- Inject the new Email Service

    @Autowired
    private TicketGenerator ticketGenerator; // Keep this for PDF generation

    @RabbitListener(queues = RabbitMQConfig.QUEUE) // Use the constant from Config, or "notification_queue"
    public void consumeMessageFromQueue(BookingMessage message) {
        System.out.println("🐇 Received Message: " + message);

        try {
            // 1. Generate PDF Ticket (Your existing logic)
            ticketGenerator.generateTicket(message.getBookingId(), message.getMovieName(), message.getSeatNumber());

            // 2. Send Email Confirmation (The new logic)
            if (message.getEmail() != null && !message.getEmail().isEmpty()) {
                emailService.sendBookingConfirmation(
                    message.getEmail(),
                    message.getMovieName(),
                    message.getSeatNumber(),
                    String.valueOf(message.getBookingId())
                );
            } else {
                System.out.println("⚠️ No email provided in booking message. Skipping email.");
            }

        } catch (Exception e) {
            System.err.println("❌ Error processing notification: " + e.getMessage());
        }
    }
}