package com.hexaware.serviceImplementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.CartDTO;
import com.hexaware.entity.Cart;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Menu;
import com.hexaware.exceptions.ResourceNotFoundException;
import com.hexaware.mapper.CartMapper;
import com.hexaware.repository.CartRepository;
import com.hexaware.repository.CustomerRepository;
import com.hexaware.repository.MenuRepository;
import com.hexaware.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public CartDTO addToCart(CartDTO cartDTO) {
        // Don't rely on ModelMapper here
        Cart cart = new Cart();

        // Fetch Menu and Customer
        Menu menu = menuRepository.findById(cartDTO.getMenu().getMenuId())
                .orElseThrow(() -> new ResourceNotFoundException("Menu not found"));

        Customer customer = customerRepository.findById(cartDTO.getCustomerDTO().getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        // Manually set fields
        cart.setMenu(menu);
        cart.setCustomer(customer);
        cart.setQuantity(cartDTO.getQuantity());

        // Calculate total price
        BigDecimal total = menu.getPrice().multiply(BigDecimal.valueOf(cartDTO.getQuantity()));
        cart.setTotal(total);

        // Save cart
        Cart savedCart = cartRepository.save(cart);

        // Return DTO
        return cartMapper.cartToCartDto(savedCart);
    }


    @Override
    public List<CartDTO> viewCartByCustomer(int customerId) {
        List<Cart> carts = cartRepository.findByCustomerCustomerId(customerId);
        return carts.stream().map(cartMapper::cartToCartDto).collect(Collectors.toList());
    }

    @Override
    public CartDTO updateCartItem(int cartId, CartDTO cartDTO) {
        Cart existingCart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        Menu menu = menuRepository.findById(cartDTO.getMenu().getMenuId())
                .orElseThrow(() -> new ResourceNotFoundException("Menu not found"));

        existingCart.setQuantity(cartDTO.getQuantity());
        existingCart.setMenu(menu);
        BigDecimal newTotal = menu.getPrice().multiply(BigDecimal.valueOf(cartDTO.getQuantity()));
        existingCart.setTotal(newTotal);

        Cart updatedCart = cartRepository.save(existingCart);
        return cartMapper.cartToCartDto(updatedCart);
    }

    @Override
    public void removeCartItem(int cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cartRepository.delete(cart);
    }
    
    @Override
    public void clearCartByCustomerId(int customerId) {
        List<Cart> carts = cartRepository.findByCustomerCustomerId(customerId);
        cartRepository.deleteAll(carts);
    }

}
