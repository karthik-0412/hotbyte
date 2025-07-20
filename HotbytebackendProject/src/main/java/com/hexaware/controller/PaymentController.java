package com.hexaware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.PaymentDTO;
import com.hexaware.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin("http://localhost:3000")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay/{orderId}")
    public ResponseEntity<PaymentDTO> makePayment(@RequestBody PaymentDTO paymentDTO, @PathVariable int orderId) {
        return ResponseEntity.ok(paymentService.makePayment(paymentDTO, orderId));
    }

  

    @GetMapping("/history")
    public ResponseEntity<List<PaymentDTO>> viewAllPaymentHistory() {
        return ResponseEntity.ok(paymentService.viewAllPaymentHistory());
    }

}