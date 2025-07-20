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

import com.hexaware.dto.CartDTO;
import com.hexaware.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("http://localhost:3000")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addToCart(@RequestBody CartDTO cartDTO) {
        return ResponseEntity.ok(cartService.addToCart(cartDTO));
    }

    @GetMapping("/view/{customerId}")
    public ResponseEntity<List<CartDTO>> viewCart(@PathVariable int customerId) {
        return ResponseEntity.ok(cartService.viewCartByCustomer(customerId));
    }

    @PutMapping("/update/{cartId}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable int cartId, @RequestBody CartDTO cartDTO) {
        return ResponseEntity.ok(cartService.updateCartItem(cartId, cartDTO));
    }

    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable int cartId) {
        cartService.removeCartItem(cartId);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/clear/{customerId}")
    public ResponseEntity<Void> clearCart(@PathVariable int customerId) {
        cartService.clearCartByCustomerId(customerId);
        return ResponseEntity.noContent().build();
    }

}
