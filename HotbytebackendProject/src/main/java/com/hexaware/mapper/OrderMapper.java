package com.hexaware.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.dto.MenuDTO;
import com.hexaware.dto.OrderDTO;
import com.hexaware.dto.OrderItemDTO;
import com.hexaware.entity.Menu;
import com.hexaware.entity.Order;
import com.hexaware.entity.OrderItem;
import com.hexaware.repository.OrderItemRepository;

@Component
public class OrderMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Map OrderDTO to Order
    public Order orderDtoToOrder(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    // Map Order to OrderDTO (with items)
    public OrderDTO orderToOrderDto(Order order) {
        OrderDTO dto = modelMapper.map(order, OrderDTO.class);

        // Fetch order items for this order
        List<OrderItem> orderItems = orderItemRepository.findByOrderOrderId(order.getOrderId());

        List<OrderItemDTO> itemDTOs = orderItems.stream()
            .map(orderItem -> {
                OrderItemDTO itemDTO = new OrderItemDTO();
                itemDTO.setOrderItemId(orderItem.getOrderItemId());
                itemDTO.setQuantity(orderItem.getQuantity());
                itemDTO.setPrice(orderItem.getPrice());

                // Set the menu inside itemDTO
                Menu menu = orderItem.getMenu();
                if (menu != null) {
                    MenuDTO menuDTO = modelMapper.map(menu, MenuDTO.class);
                    itemDTO.setMenu(menuDTO);
                }

                return itemDTO;
            })
            .collect(Collectors.toList());

        dto.setItems(itemDTOs);

        return dto;
    }
}
