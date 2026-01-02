package com.hexaware.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.OrderCreateDTO;
import com.hexaware.dto.OrderDTO;
import com.hexaware.dto.RatingDTO;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Restaurant;
import com.hexaware.enums.OrderStatus;
import com.hexaware.service.CustomerService;
import com.hexaware.service.OrderService;
import com.hexaware.service.RestaurantService;
@RestController
@RequestMapping("api/order")
@CrossOrigin("http://localhost:3000")
public class OrderController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private OrderService orderService;


	
	@PostMapping("/placeorder/{restaurantId}")
	public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderCreateDTO orderCreateDTO,
	                                           Principal principal,
	                                           @PathVariable int restaurantId) {
	    OrderDTO placed = orderService.placeOrder(orderCreateDTO, principal.getName(), restaurantId);
	    return ResponseEntity.ok(placed);
	}



    @GetMapping("/history")
    public ResponseEntity<List<OrderDTO>> getOrderHistory(@RequestParam Integer customerId) {
        return ResponseEntity.ok(orderService.getOrderHistory(customerId));
    }
    
 // Order Management APIs
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> viewOrders() {
        return ResponseEntity.ok(orderService.viewOrders());
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable int id, @RequestBody Map<String, String> request) {
        OrderStatus status = OrderStatus.valueOf(request.get("status").toUpperCase());
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }
    
    @GetMapping("/myorders")
    public ResponseEntity<List<OrderDTO>> getOrdersByLoggedInCustomer(Principal principal) {
        return ResponseEntity.ok(orderService.getOrdersByCustomerUsername(principal.getName()));
    }
    
    @GetMapping("/restaurant/{restaurantId}/orders")
    public ResponseEntity<List<OrderDTO>> getOrdersByRestaurant(@PathVariable int restaurantId) {
        List<OrderDTO> orders = orderService.getOrdersByRestaurant(restaurantId);
        return ResponseEntity.ok(orders);
    }


    
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    
    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable int id) {
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }
    
    @GetMapping("/{id}/track")
    public ResponseEntity<OrderDTO> trackOrder(@PathVariable int id) {
        return ResponseEntity.ok(orderService.trackOrder(id));
    }
    
    @PostMapping("/orders/{orderId}/rate")
    public ResponseEntity<?> rateOrder(@PathVariable int orderId, @RequestBody RatingDTO ratingDTO) {
        orderService.rateOrder(orderId, ratingDTO.getRating(), ratingDTO.getReview());
        return ResponseEntity.ok("Order rated successfully.");
    }


}
