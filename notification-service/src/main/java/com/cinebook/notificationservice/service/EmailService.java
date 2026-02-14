package com.cinebook.notificationservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    /**
     * Sends an HTML email with a PDF attachment.
     * * @param to - Recipient email address
     * @param subject - Email subject line
     * @param htmlBody - The email content in HTML format
     * @param attachment - The PDF file object (can be null)
     */
    public void sendEmailWithAttachment(String to, String subject, String htmlBody, File attachment) {
        try {
            // 1. Create a MimeMessage (Supports HTML & Attachments)
            MimeMessage message = mailSender.createMimeMessage();
            
            // 'true' indicates this message is multipart (needed for attachments)
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            helper.setFrom(senderEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // true = Send as HTML

            // 2. Add the PDF Attachment if it exists
            if (attachment != null && attachment.exists()) {
                // The string "CineBook_Ticket.pdf" is the name the user will see in their inbox
                helper.addAttachment("CineBook_Ticket.pdf", attachment);
            }

            // 3. Send the email
            mailSender.send(message);
            System.out.println("📧 Email Sent Successfully to: " + to);

        } catch (MessagingException e) {
            System.err.println("⚠️ Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}