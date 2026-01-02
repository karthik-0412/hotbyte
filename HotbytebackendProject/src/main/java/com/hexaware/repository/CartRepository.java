package com.hexaware.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.entity.Cart;
import com.hexaware.entity.Customer;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	Optional<Cart> findByCustomer(Customer customer);


	List<Cart> findByCustomerCustomerId(int customerId);
	

	
	@Query("SELECT c FROM Cart c WHERE c.customer.customerId = ?1 AND c.menu.restaurant.restaurantId = ?2")
	List<Cart> findByCustomerIdAndRestaurantId(int customerId, int restaurantId);


//	List<Cart> findByOrderOrderId(int orderId);


}
