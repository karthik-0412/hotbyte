package com.hexaware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantStatusDTO {
    private String name;
    private String address;
    private double rating;
}

