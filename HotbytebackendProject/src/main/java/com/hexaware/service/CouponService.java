package com.hexaware.service;

import java.util.List;

import com.hexaware.dto.CouponDTO;

public interface CouponService {

    CouponDTO createCoupon(CouponDTO couponDTO);

    CouponDTO getCouponById(Long couponId);

    List<CouponDTO> getAllCoupons();

    CouponDTO updateCoupon(Long couponId, CouponDTO couponDTO);

    void deleteCoupon(Long couponId);
    
    CouponDTO getCouponByCode(String code);
}

