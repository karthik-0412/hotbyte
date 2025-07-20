package com.hexaware.dto;

import java.time.LocalDate;

import com.hexaware.enums.UserRole;
import com.hexaware.enums.UserStatus;

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

public class UserDTO {
	
	private int userId;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
    private UserRole role;
    private UserStatus status;
    private LocalDate lastLogin;
    private LocalDate memberSince;
    private Integer orders;
    private Integer restaurantId;
    private Integer customerId;


}
