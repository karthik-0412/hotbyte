package com.hexaware.serviceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hexaware.dto.OrderDTO;
import com.hexaware.dto.PaymentDTO;
import com.hexaware.entity.Order;
import com.hexaware.entity.Payment;
import com.hexaware.enums.PaymentStatus;
import com.hexaware.exceptions.ResourceNotFoundException;
import com.hexaware.mapper.OrderMapper;
import com.hexaware.mapper.PaymentMapper;
import com.hexaware.repository.OrderRepository;
import com.hexaware.repository.PaymentRepository;
import com.hexaware.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;
   
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public PaymentDTO makePayment(PaymentDTO paymentDTO, int orderId) {
        Payment payment = paymentMapper.paymentDtoToPayment(paymentDTO);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

//        payment.setOrder(order); // set full order entity
        payment.setPaymentDateTime(LocalDateTime.now());
        payment.setStatus(PaymentStatus.SUCCESS);

        payment = paymentRepository.save(payment);

        PaymentDTO paymentResponse = paymentMapper.paymentToPaymentDto(payment);

        // Map order in paymentDTO response
//        paymentResponse.setOrder(orderMapper.orderToOrderDto(order));

        return paymentResponse;
    }



//    @Override
//    public PaymentDTO getPaymentDetails(int orderId) {
//        Payment payment = paymentRepository.findByOrderOrderId(orderId)
//                .orElseThrow(() -> new ResourceNotFoundException("Payment not found for order id: " + orderId));
//
//        PaymentDTO dto = paymentMapper.paymentToPaymentDto(payment);
////        dto.setOrder(orderMapper.orderToOrderDto(payment.getOrder())); // ensure order details are mapped
//        return dto;
//    }


    @Override
    public List<PaymentDTO> viewAllPaymentHistory() {
        List<Payment> payments = paymentRepository.findAll();

        return payments.parallelStream()
                       .map(payment -> {
                           PaymentDTO dto = paymentMapper.paymentToPaymentDto(payment);
                           // Map order manually if needed
//                           dto.setOrder(orderMapper.orderToOrderDto(payment.getOrder()));
                           return dto;
                       })
                       .collect(Collectors.toList());
    }



}