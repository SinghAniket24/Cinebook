package com.cinebook.movieservice.controller;

import com.cinebook.movieservice.entity.User;
import com.cinebook.movieservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cinebook.movieservice.util.JwtUtil; // Import your new class
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired 
    private JwtUtil jwtUtil; // <--- Inject This

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> payload) {
        try {
            User user = authService.register(
                payload.get("name"), 
                payload.get("email"), 
                payload.get("password")
            );
            return ResponseEntity.ok(Map.of("message", "User registered!", "userId", user.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        try {
            User user = authService.login(payload.get("email"), payload.get("password"));
            
            // GENERATE TOKEN
            String token = jwtUtil.generateToken(user.getEmail());

            return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "userId", user.getId(),
                "name", user.getName(),
                "email", user.getEmail(),
                "token", token // <--- Send Token to Frontend
            ));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }
}