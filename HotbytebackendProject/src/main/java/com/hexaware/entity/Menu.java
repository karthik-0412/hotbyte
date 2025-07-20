package com.hexaware.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuId;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "item_name")
    private String itemName;

    @NotNull
    @Size(min = 5, max = 500)
    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @DecimalMin(value = "0.0")
    @Column(name = "original_price")
    private BigDecimal originalPrice;

    @Min(0)
    @Max(100)
    @Column(name = "offer")
    private Integer offer;

    private Double rating;

    @Column(name = "image_url", columnDefinition = "LONGTEXT")
    private String imageUrl;

    @Column(name = "dietary_info")
    private String dietaryInfo;

    @Column(name = "taste_info")
    private String tasteInfo;

    @Column(name = "availability")
    private String availability;

    @Column(name = "cooking_time")
    private Integer cookingTime; 
    private Integer calories;
    private Double proteins;
    private Double fats;
    private Double carbs;

    private String ingredients;
    private String location;

    @Column(name = "availability_time")
    private String availabilityTime;

    @Column(name = "added_on")
    private LocalDate addedOn;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private MenuCategory category;
}
