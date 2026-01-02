package com.hexaware.serviceImplementation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.AdminDashboardDTO;
import com.hexaware.dto.RecentOrderDTO;
import com.hexaware.dto.RestaurantStatusDTO;
import com.hexaware.entity.Menu;
import com.hexaware.entity.Order;
import com.hexaware.entity.Restaurant;
import com.hexaware.entity.User;
import com.hexaware.repository.MenuRepository;
import com.hexaware.repository.OrderRepository;
import com.hexaware.repository.RestaurantRepository;
import com.hexaware.repository.UserRepository;
import com.hexaware.service.AdminService;


@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	MenuRepository menuRepository;
	
	@Override
	public AdminDashboardDTO getAdminDashboardData() {
	    List<User> users = userRepository.findAll();
	    List<Restaurant> restaurants = restaurantRepository.findAll();
	    List<Order> orders = orderRepository.findAll();
	    List<Menu> menus = menuRepository.findAll();

	    BigDecimal revenue = orders.stream()
	            .map(Order::getTotalPrice)
	            .filter(Objects::nonNull)
	            .reduce(BigDecimal.ZERO, BigDecimal::add);

	    BigDecimal avgOrderValue = orders.isEmpty() ? BigDecimal.ZERO :
	            revenue.divide(BigDecimal.valueOf(orders.size()), 2, RoundingMode.HALF_UP);

	    BigDecimal commission = revenue.multiply(BigDecimal.valueOf(0.10));

	    List<RecentOrderDTO> recentOrders = orders.stream()
	    		.sorted(Comparator.comparing(Order::getOrderDate).reversed())
	            .limit(4)
	            .map(order -> new RecentOrderDTO(
	                    order.getOrderId(),
	                    order.getCustomer() != null ? order.getCustomer().getUser().getName() : "Order #" + order.getOrderId(),
	                    order.getRestaurant() != null ? order.getRestaurant().getRestaurantName() : "N/A",
	                    order.getTotalPrice(),
	                    order.getStatus().toString().toLowerCase()
	            )).collect(Collectors.toList());

	    List<RestaurantStatusDTO> restaurantStatuses = restaurants.stream()
	    		.sorted(Comparator.comparing(Restaurant::getRating, Comparator.nullsLast(Double::compareTo)).reversed())
	    	    .limit(5)
	            .map(r -> new RestaurantStatusDTO(
	                    r.getRestaurantName(),
	                    r.getAddress() != null ? r.getAddress().getCity() : "N/A",
	                    r.getRating() != null ? r.getRating() : 4.5
	            )).collect(Collectors.toList());

	    return new AdminDashboardDTO(
	            users.size(),
	            restaurants.size(),
	            orders.size(),
	            revenue,
	            avgOrderValue,
	            commission,
	            menus.size(),
	            recentOrders,
	            restaurantStatuses
	    );
	}

}
