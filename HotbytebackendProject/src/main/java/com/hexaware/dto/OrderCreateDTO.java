package com.hexaware.dto;

import java.math.BigDecimal;
import java.util.List;

import com.hexaware.enums.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDTO {
    private String specialInstructions;
    private BigDecimal totalPrice; // ❌ Not necessary – calculated on backend
    private PaymentMethod paymentMethod;
    private AddressDTO address;
    private List<CartRefDTO> items;
    private String couponCode; // ✅ Good
    private BigDecimal deliveryFee; // ✅ Optional: sent from frontend
    private BigDecimal tax;
    private BigDecimal discount;

    private BigDecimal subtotal;
}

