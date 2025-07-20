package com.hexaware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuFilterDto {
    private String category;
    private String foodType;
    private Double maxPrice;

}