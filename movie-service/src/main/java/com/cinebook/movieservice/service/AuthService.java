package com.cinebook.movieservice.service;

import com.cinebook.movieservice.entity.User;
import com.cinebook.movieservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    // Standard encryption for passwords
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User register(String name, String email, String password) {
        if (userRepo.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(name, email, encodedPassword);
        return userRepo.save(newUser);
    }

    public User login(String email, String password) {
        Optional<User> userOpt = userRepo.findByEmail(email);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Check if raw password matches the stored hash
            if (passwordEncoder.matches(password, user.getPasswordHash())) {
                return user;
            }
        }
        throw new RuntimeException("Invalid email or password");
    }
}