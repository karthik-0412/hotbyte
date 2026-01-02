package com.hexaware.service;

import java.util.List;

import com.hexaware.dto.CustomerDTO;
import com.hexaware.dto.OrderCreateDTO;
//import com.hexaware.dto.OrderCreateDTO;
import com.hexaware.dto.OrderDTO;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Restaurant;
import com.hexaware.enums.OrderStatus;

public interface OrderService {
	
	OrderDTO placeOrder(OrderCreateDTO orderDTO, String username, int restaurantId);
//	public OrderDTO placeOrder(OrderDTO createDTO);
    List<OrderDTO> getOrderHistory(int customerId);
	
	// Order Management
    List<OrderDTO> viewOrders();
    OrderDTO updateOrderStatus(int id, OrderStatus status);
    
    OrderDTO getOrderById(int id);
//    OrderDTO viewOrderHistory();
    OrderDTO cancelOrder(int id);
//    OrderDTO updateOrderStatus(int id, OrderStatus status);
    OrderDTO trackOrder(int id);
    
    List<OrderDTO> getOrdersByCustomerUsername(String username);
	List<OrderDTO> getOrdersByRestaurant(int restaurantId);
	List<OrderDTO> viewOrders(String username);
	void rateOrder(int orderId, int rating, String review);

}
