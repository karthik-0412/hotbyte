package com.hexaware.dto;

import lombok.Data;

@Data
public class ContactForm {
    private String name;
    private String email;
    private String phone;
    private String subject;
    private String category;
    private String message;
}