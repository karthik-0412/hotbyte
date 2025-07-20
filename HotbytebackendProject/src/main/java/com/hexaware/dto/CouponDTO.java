package com.hexaware.dto;

import java.time.LocalDate;
import java.util.Date;

import com.hexaware.enums.CouponStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO {
	private Long couponId;
    private String name;
    private String code;
    private String description;
    private Integer discount;         // could be percentage or fixed value
    private Double minOrder;
    private Double maxDiscount;
    private Integer usageLimit;
    private Integer usageCount;               // how many times used
    private LocalDate expiry;
    private CouponStatus status; 

}
