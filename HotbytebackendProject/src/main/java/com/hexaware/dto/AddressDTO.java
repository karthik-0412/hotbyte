package com.hexaware.dto;

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

public class AddressDTO {
	
	private int addressId;	

    private String street;

    private String city;

    private String state;

    private String pincode;
    
//    private CustomerDTO customerDTO;

}
