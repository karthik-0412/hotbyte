package com.hexaware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.entity.OrderItem;
import com.hexaware.entity.Restaurant;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    
    // Get all items by order ID
    List<OrderItem> findByOrderOrderId(int orderId);

    // Optional: Get all items by customer ID (via order)
    List<OrderItem> findByOrderCustomerCustomerId(int customerId);

	List<OrderItem> findByOrderRestaurant(Restaurant restaurant);
}
