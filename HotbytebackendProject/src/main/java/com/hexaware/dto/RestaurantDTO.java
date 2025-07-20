package com.hexaware.dto;

import java.time.LocalTime;

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

public class RestaurantDTO {
	
	private int restaurantId;

    private String restaurantName;

    private String location;

//    private String cuisineType;
    private String description;
    
    private Double rating;

//    private String openingTime;
//
//    private String closingTime;

    private String status;
    
    private UserCreateDTO userdto;
    
    private AddressDTO addressdto;



}
