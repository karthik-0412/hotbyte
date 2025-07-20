package com.hexaware.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.dto.OrderDTO;
import com.hexaware.dto.PaymentDTO;
import com.hexaware.entity.Order;
import com.hexaware.entity.Payment;

@Component
public class PaymentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Payment paymentDtoToPayment(PaymentDTO paymentDTO) {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);

        return payment;
    }

    public PaymentDTO paymentToPaymentDto(Payment payment) {
        PaymentDTO dto = modelMapper.map(payment, PaymentDTO.class);

        return dto;
    }
}
