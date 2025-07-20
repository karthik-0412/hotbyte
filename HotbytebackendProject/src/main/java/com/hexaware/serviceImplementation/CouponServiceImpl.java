package com.hexaware.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.CouponDTO;
import com.hexaware.entity.Coupon;
import com.hexaware.exceptions.ResourceNotFoundException;
import com.hexaware.mapper.CouponMapper;
import com.hexaware.repository.CouponRepository;
import com.hexaware.service.CouponService;

import jakarta.transaction.Transactional;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponMapper couponMapper;

    @Transactional
    @Override
    public CouponDTO createCoupon(CouponDTO couponDTO) {
        Coupon coupon = couponMapper.couponDtoToCoupon(couponDTO);
        coupon.setCouponId(null); // Ensure ID is null for new entity
        Coupon saved = couponRepository.save(coupon);
        return couponMapper.couponToCouponDto(saved);
    }
//    couponToCouponDto
//    couponDtoToCoupon
    @Override
    public CouponDTO getCouponById(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
            .orElseThrow(() -> new ResourceNotFoundException("Coupon not found with id: " + couponId));
        return couponMapper.couponToCouponDto(coupon);
    }

    @Override
    public List<CouponDTO> getAllCoupons() {
        return couponRepository.findAll().stream()
            .map(couponMapper::couponToCouponDto)
            .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CouponDTO updateCoupon(Long couponId, CouponDTO couponDTO) {
        Coupon existing = couponRepository.findById(couponId)
            .orElseThrow(() -> new ResourceNotFoundException("Coupon not found with id: " + couponId));

        existing.setCode(couponDTO.getCode());
        existing.setName(couponDTO.getName());
        existing.setDescription(couponDTO.getDescription());
        existing.setDiscount(couponDTO.getDiscount());
        existing.setMinOrder(couponDTO.getMinOrder());
        existing.setMaxDiscount(couponDTO.getMaxDiscount());
        existing.setUsageLimit(couponDTO.getUsageLimit());
        existing.setUsageCount(couponDTO.getUsageCount());
        existing.setExpiry(couponDTO.getExpiry());
        existing.setStatus(couponDTO.getStatus());

        Coupon updated = couponRepository.save(existing);
        return couponMapper.couponToCouponDto(updated);
    }


    @Override
    public void deleteCoupon(Long couponId) {
        if (!couponRepository.existsById(couponId)) {
            throw new ResourceNotFoundException("Coupon not found with id: " + couponId);
        }
        couponRepository.deleteById(couponId);
    }
    
    @Override
    public CouponDTO getCouponByCode(String code) {
        return couponMapper.couponToCouponDto(couponRepository.findByCode(code));
    }
}
