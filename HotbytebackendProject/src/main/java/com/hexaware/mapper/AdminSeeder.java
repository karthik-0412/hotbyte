package com.hexaware.mapper;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hexaware.entity.User;
import com.hexaware.enums.UserRole;
import com.hexaware.enums.UserStatus;
import com.hexaware.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class AdminSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String adminEmail = "admin@hotbyte.com";

        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // You can hash this securely
            admin.setName("Super Admin");
            admin.setEmail(adminEmail);
            admin.setPhoneNumber("9000000000");
            admin.setRole(UserRole.ADMIN);
            admin.setStatus(UserStatus.ACTIVE);
            admin.setOrders(0);
            admin.setMemberSince(LocalDate.now());
            admin.setLastLogin(null); // Or set to now if needed

            userRepository.save(admin);
            System.out.println("✅ Admin user seeded successfully.");
        } else {
            System.out.println("ℹ️ Admin user already exists.");
        }
    }
}
