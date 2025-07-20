package com.hexaware.service;

import java.util.List;

import com.hexaware.dto.MenuCategoryDTO;
import com.hexaware.dto.MenuDTO;
import com.hexaware.dto.OrderDTO;
import com.hexaware.dto.RestaurantDTO;
import com.hexaware.enums.OrderStatus;

public interface RestaurantService {

    // Restaurant Management
    RestaurantDTO registerRestaurant(RestaurantDTO restaurantDTO);
    RestaurantDTO getRestaurant(int restaurantId);
    RestaurantDTO updateRestaurant(int restaurantId, RestaurantDTO restaurantDTO);
    void deleteRestaurant(int restaurantId);
    List<RestaurantDTO> getAllRestaurants();
	RestaurantDTO getRestaurantByUserName(String username);
	RestaurantDTO updateRestaurantStatus(int restaurantId, String status);
	RestaurantDTO toggleStatusByUsername(String username, boolean open);

//    // Menu Management
//    MenuDTO addMenuItem(MenuDTO menuDTO);
//    MenuDTO updateMenuItem(int id, MenuDTO menuDTO);
//    void deleteMenuItem(int id);
//    List<MenuDTO> viewAllMenus();

//    // Order Management
//    List<OrderDTO> viewOrders();
//    OrderDTO updateOrderStatus(int id, OrderStatus status);
    
//    //MenuCategoryManagement
//    MenuCategoryDTO addMenuCategory(MenuCategoryDTO categoryDTO);
//    List<MenuCategoryDTO> getAllMenuCategories();

}