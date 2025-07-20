package com.hexaware.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuDTO {
    private int menuId;
    private String itemName;
    private String description;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private Integer offer;
    private Double rating;
    private String imageUrl;
    private String dietaryInfo;
    private String tasteInfo;
    private String availability;
    private Integer cookingTime;
    private Integer calories;
    private Double proteins;
    private Double fats;
    private Double carbs;
    private String ingredients;
    private String location;
    private String availabilityTime;
    private LocalDate addedOn;
    private RestaurantDTO restaurant;
    private MenuCategoryDTO category;
}
