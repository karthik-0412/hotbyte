package com.hexaware.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    private int totalOrders;
    private int menuItemCount;
    private double todaysRevenue;
    private int averagePrepTime;
    private List<OrderSummaryDTO> recentOrders;
    private List<PopularItemDTO> popularItems;
}
