package com.hexaware.entity;


import java.time.LocalDate;

import com.hexaware.enums.CouponStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    private String code;
    private String name;
    private String description;
    private Integer discount;
    private Double minOrder;
    private Double maxDiscount;
    private Integer usageLimit;
    @Column(name = "usage_count") // if you want to use a custom column name
    private Integer usageCount;
    private LocalDate expiry;
    private CouponStatus status; // active, inactive, expired

    // Getters and Setters
}
