package com.hexaware.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.hexaware.enums.OrderStatus;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {

    private int orderId;

    private RestaurantPublicDTO restaurant;

    private BigDecimal totalPrice;

    private BigDecimal tax;

    private BigDecimal discount;

    private BigDecimal deliveryFee;

    private LocalDateTime orderDate;

    private OrderStatus status;

    private AddressDTO address;

    private PaymentDTO payment;

    private String estimatedDelivery;

    private Integer rating;

    private String review;

    private List<OrderItemDTO> items;

    private CouponDTO coupon;
    
    private BigDecimal subtotal;

}
