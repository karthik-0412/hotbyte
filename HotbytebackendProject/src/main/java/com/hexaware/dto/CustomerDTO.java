package com.hexaware.dto;

import com.hexaware.enums.Gender;

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

public class CustomerDTO {

	private int customerId;

    private String fullName;

    private int age;

    private Gender gender;

    private String mobileNumber;   
    
//    private UserDTO userdto;
    
    private AddressDTO address;
    private UserCreateDTO userdto;

}
