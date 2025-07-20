package com.hexaware.dto;

import java.time.LocalTime;

import com.hexaware.enums.RestaurantStatus;

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

public class RestaurantPublicDTO {
	
	private int restaurantId;
	
	private String restaurantName;

    private String location;

    private String imageUrl;

    private RestaurantStatus status;  

    private LocalTime openingTime;

    private LocalTime closingTime;

}
