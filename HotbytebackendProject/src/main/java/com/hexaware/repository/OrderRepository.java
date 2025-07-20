package com.hexaware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.entity.Customer;
import com.hexaware.entity.Order;
import com.hexaware.entity.Restaurant;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findByCustomerCustomerId(int customerId);

    List<Order> findByRestaurantRestaurantId(int restaurantId);
    
    List<Order> findByOrderId(int orderId);
    
    int countByCustomer_User_UserId(int userId);
    
    List<Order> findByCustomer(Customer customer);

    List<Order> findByRestaurant(Restaurant restaurant);

	List<Order> findByRestaurantOrderByOrderDateDesc(Restaurant restaurant);







}
