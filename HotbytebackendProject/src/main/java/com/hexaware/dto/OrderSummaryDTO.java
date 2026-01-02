package com.hexaware.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSummaryDTO {
    private int id;                          // Order ID
    private List<OrderItemDTO> items;
    private BigDecimal total;
    private String status;
}

