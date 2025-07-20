package com.hexaware.mapper;

import org.modelmapper.ModelMapper;
//import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.dto.CouponDTO;
import com.hexaware.entity.Coupon;

import jakarta.annotation.PostConstruct;

@Component
public class CouponMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Coupon couponDtoToCoupon(CouponDTO dto) {
        return modelMapper.map(dto, Coupon.class);
    }

    public CouponDTO couponToCouponDto(Coupon coupon) {
        return modelMapper.map(coupon, CouponDTO.class);
    }

    @PostConstruct
    public void init() {
    	modelMapper.typeMap(CouponDTO.class, Coupon.class).addMappings(mapper -> {
    	    mapper.skip((destination, value) -> destination.setCouponId((Long) value));
    	});
    }
        // Add any additional custom mappings here if needed
 
}
