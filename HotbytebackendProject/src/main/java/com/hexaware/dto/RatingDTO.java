package com.hexaware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
	 private int orderId;
	    private Integer rating; // allow nulls
	    private String review;

}
