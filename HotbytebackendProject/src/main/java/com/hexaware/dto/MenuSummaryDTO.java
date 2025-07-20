package com.hexaware.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuSummaryDTO {
	private String itemName;
    private String imageUrl;
    private BigDecimal price;
    private BigDecimal total;
}
