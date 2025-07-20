package com.hexaware.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.dto.CartDTO;
import com.hexaware.dto.CustomerDTO;
import com.hexaware.entity.Cart;
import com.hexaware.entity.Customer;

import jakarta.annotation.PostConstruct;

@Component
public class CartMapper {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void setup() {
        modelMapper.addMappings(new PropertyMap<CartDTO, Cart>() {
            @Override
            protected void configure() {
                using(ctx -> modelMapper.map(((CartDTO) ctx.getSource()).getCustomerDTO(), Customer.class))
                    .map(source, destination.getCustomer());
            }
        });

        modelMapper.addMappings(new PropertyMap<Cart, CartDTO>() {
            @Override
            protected void configure() {
                using(ctx -> modelMapper.map(((Cart) ctx.getSource()).getCustomer(), CustomerDTO.class))
                    .map(source, destination.getCustomerDTO());
            }
        });
    }

    public Cart cartDtoToCart(CartDTO dto) {
        return modelMapper.map(dto, Cart.class);
    }

    public CartDTO cartToCartDto(Cart cart) {
        return modelMapper.map(cart, CartDTO.class);
    }
}

