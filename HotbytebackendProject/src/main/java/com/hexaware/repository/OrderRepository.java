package com.hexaware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

//	List<Order> findTop3ByRestaurantOrderByCreatedAtDesc(Restaurant restaurant);

	int countByRestaurant(Restaurant restaurant);

	@Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Order o WHERE o.restaurant.restaurantId = :restaurantId AND o.orderDate >= CURRENT_DATE")
	Double getTodaysRevenueForRestaurant(@Param("restaurantId") int restaurantId);
	
	 @Query("SELECT AVG(o.preparationTime) FROM Order o WHERE o.restaurant.restaurantId = :restaurantId")
	 Double getAveragePrepTimeForRestaurant(@Param("restaurantId") int restaurantId);
	
	List<Order> findTop3ByRestaurantOrderByOrderDateDesc(Restaurant restaurant);

//	List<Order> findTop3ByRestaurantOrderByOrderDateDesc(Restaurant restaurant);







}
