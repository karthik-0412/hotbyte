package com.hexaware.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDashboardDTO {
    private int users;
    private int restaurants;
    private int orders;
    private BigDecimal revenue;
    private BigDecimal avgOrderValue;
    private BigDecimal commission;
    private int menuItems;
    private List<RecentOrderDTO> recentOrders;
    private List<RestaurantStatusDTO> restaurantStatus;
}
