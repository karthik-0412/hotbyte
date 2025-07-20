package com.hexaware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.CouponDTO;
import com.hexaware.mapper.CouponMapper;
import com.hexaware.service.CouponService;


@RestController
@RequestMapping("/api/coupons")
@CrossOrigin("http://localhost:3000")
public class CouponController {

    private final CouponMapper couponMapper;

    @Autowired
    private CouponService couponService;

    CouponController(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    // Create Coupon
    @PostMapping
    public ResponseEntity<CouponDTO> createCoupon(@RequestBody CouponDTO couponDTO) {
        CouponDTO created = couponService.createCoupon(couponDTO);
        return ResponseEntity.ok(created);
    }

    // Get Coupon by ID
    @GetMapping("/{couponId}")
    public ResponseEntity<CouponDTO> getCouponById(@PathVariable Long couponId) {
        return ResponseEntity.ok(couponService.getCouponById(couponId));
    }

    // Get All Coupons
    @GetMapping("/all")
    public ResponseEntity<List<CouponDTO>> getAllCoupons() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    // Update Coupon
    @PutMapping("/{couponId}")
    public ResponseEntity<CouponDTO> updateCoupon(@PathVariable Long couponId, @RequestBody CouponDTO couponDTO) {
        return ResponseEntity.ok(couponService.updateCoupon(couponId, couponDTO));
    }

    // Delete Coupon
    @DeleteMapping("/{couponId}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long couponId) {
        couponService.deleteCoupon(couponId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/code/{code}")
    public ResponseEntity<CouponDTO> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(couponService.getCouponByCode(code));
    }
}

