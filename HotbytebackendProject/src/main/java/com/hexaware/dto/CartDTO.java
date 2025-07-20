package com.hexaware.dto;

import java.math.BigDecimal;
import java.util.List;

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

public class CartDTO {
	
	 private int cartId;
	 
	 private MenuDTO menu; 
	 
	 private int quantity;
	 
	 private BigDecimal total;
	 
	 private CustomerDTO customerDTO;
	 
	 private MenuSummaryDTO menus;
	 
	 private Integer orderId;

}
