package com.hexaware.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentOrderDTO {
    private int id;
    private String name;
    private String restaurant;
    private BigDecimal amount;
    private String status;
}

