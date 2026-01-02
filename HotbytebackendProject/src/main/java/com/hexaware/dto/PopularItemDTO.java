package com.hexaware.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopularItemDTO {
	private String name;
    private String category;
    private BigDecimal price; // ✅ matches entity
    private Integer avgPrepTime; // ✅ matches entity
    private String imageUrl;
}
