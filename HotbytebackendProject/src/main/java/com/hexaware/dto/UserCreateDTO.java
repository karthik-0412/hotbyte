package com.hexaware.dto;

import com.hexaware.enums.UserRole;
import com.hexaware.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreateDTO {
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
    private String password; // ✅ here it's allowed
    private UserRole role;
    private UserStatus status;
    
}
