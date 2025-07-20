package com.hexaware.service;

import java.util.List;

import com.hexaware.dto.PaymentDTO;

public interface PaymentService {
	PaymentDTO makePayment(PaymentDTO paymentDTO,int orderId);
//    PaymentDTO getPaymentDetails(int orderId);
    List<PaymentDTO> viewAllPaymentHistory();
}
