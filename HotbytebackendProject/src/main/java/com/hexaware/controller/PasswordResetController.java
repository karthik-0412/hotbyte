package com.hexaware.controller;

import com.hexaware.entity.PasswordResetToken;
import com.hexaware.repository.UserRepository;
import com.hexaware.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:3000")
public class PasswordResetController {
    @Autowired
    private PasswordResetService resetService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/request-reset")
    public ResponseEntity<?> requestReset(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        if (userRepository.findByUsername(email).isEmpty()) {
            return ResponseEntity.badRequest().body("No user with that email.");
        }
        resetService.createAndSendToken(email);
        return ResponseEntity.ok("Reset link sent to your email.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        String newPassword = body.get("newPassword");
        var resetTokenOpt = resetService.validateToken(token);
        if (resetTokenOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
        PasswordResetToken resetToken = resetTokenOpt.get();
        var userOpt = userRepository.findByUsername(resetToken.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found.");
        }
        var user = userOpt.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        resetService.deleteToken(resetToken);
        return ResponseEntity.ok("Password reset successful.");
    }
} 