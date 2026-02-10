package com.cinebook.movieservice.service;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    // Change this if you deploy to the cloud!
    private static final String FRONTEND_URL = "http://localhost:8082";

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    public String createCheckoutSession(double amountInINR) {
        try {
            // 1. Convert INR to USD (Rate: 86)
            BigDecimal amountInR = BigDecimal.valueOf(amountInINR);
            BigDecimal conversionRate = BigDecimal.valueOf(86.0);
            BigDecimal amountInUSD = amountInR.divide(conversionRate, 2, RoundingMode.HALF_UP);
            long amountInCents = amountInUSD.multiply(BigDecimal.valueOf(100)).longValue();

            // 2. Create Session
            SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                // SUCCESS: Goes to the "Ticket Page"
                .setSuccessUrl(FRONTEND_URL + "/success.html")
                // CANCEL: Goes to the "Retry Page"
                .setCancelUrl(FRONTEND_URL + "/cancel.html")
                .addLineItem(
                    SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount(amountInCents)
                                .setProductData(
                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("CineBook Tickets")
                                        .setDescription("Movie Booking (Via Stripe)")
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .build();

            Session session = Session.create(params);
            return session.getUrl();

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle error gracefully in Controller
        }
    }
}