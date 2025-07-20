package com.hexaware.entity;


import com.hexaware.enums.Gender;

import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name = "customers")

public class Customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
    private int customerId;

    @NotNull(message = "name should not be null")
    @Size(min = 3, max = 100)
    @Column(name = "full_name")
    private String fullName;
	
	@NotNull(message = "Age should not be null")
	@Min(16)
    @Max(100)
    private int age;

    @NotNull(message = "Gender should not be null")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Mobile number should not be null")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    @Column(name = "mobile_number", unique = true)
    private String mobileNumber;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

   
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;


}
