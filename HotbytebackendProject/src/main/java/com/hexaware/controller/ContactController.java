package com.hexaware.controller;
import com.hexaware.dto.ContactForm;
import com.hexaware.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
@CrossOrigin("http://localhost:3000") 
@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendContactMessage(@RequestBody ContactForm form) {
        try {
            System.out.println("Received form: " + form); // Debug line

            String subject = "New Contact Message: " + form.getSubject();
            String content = buildEmailBody(form);
            String adminEmail = "hotbyteconnect@gmail.com";

            emailService.sendContactEmail(adminEmail, subject, content);
            return ResponseEntity.ok("Message sent successfully");

        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error sending message: " + e.getMessage());
        }
    }


    private String buildEmailBody(ContactForm form) {
        return String.format("""
                You've received a new contact message:

                Name: %s
                Email: %s
                Phone: %s
                Category: %s
                Subject: %s

                Message:
                %s
                """,
                form.getName(),
                form.getEmail(),
                form.getPhone(),
                form.getCategory(),
                form.getSubject(),
                form.getMessage()
        );
    }
}