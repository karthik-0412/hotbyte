package com.hexaware.service;

import java.util.List;

import com.hexaware.dto.CartDTO;

public interface CartService {
	CartDTO addToCart(CartDTO cartDTO);
    List<CartDTO> viewCartByCustomer(int customerId);
    CartDTO updateCartItem(int cartId, CartDTO cartDTO);
    void removeCartItem(int cartId);
    void clearCartByCustomerId(int customerId);
}
