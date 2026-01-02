package com.hexaware.entity;

import java.time.LocalTime;
import com.hexaware.enums.RestaurantStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter  
@Setter   
@AllArgsConstructor    
@NoArgsConstructor   
@ToString   

@Entity
@Table(name = "restaurants")
public class Restaurant {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurant_id")
    private int restaurantId;

    @NotNull(message = "Restaurant name should not be null")
    @Size(min = 3, max = 50)
    @Column(name = "restaurant_name")
    private String restaurantName;
    
    @NotNull(message = "Location should not be null")
    private String location;


    @NotNull(message = "Status should not be null")
    @Enumerated(EnumType.STRING)
    private RestaurantStatus status; 


    @NotNull(message = "Description should not be null")
    private String description;
    
    @NotNull
    @Column(name = "rating")
    private Double rating;
    
    @OneToOne
    @JoinColumn(name="address_id", nullable = false)
    private Address address;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "opening_time")
    private LocalTime openingTime;

    @Column(name = "closing_time")
    private LocalTime closingTime;

    @Column(name = "delivery_radius")
    private Double deliveryRadius;

    @Column(name = "min_order_amount")
    private Double minOrderAmount;



}
