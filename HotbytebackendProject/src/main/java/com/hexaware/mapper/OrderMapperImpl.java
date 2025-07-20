//package com.hexaware.mapper;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.hexaware.dto.MenuDTO;
//import com.hexaware.dto.MenuSummaryDTO;
//import com.hexaware.dto.OrderDTO;
//import com.hexaware.dto.OrderItemDTO;
//import com.hexaware.entity.Menu;
//import com.hexaware.entity.Order;
//import com.hexaware.entity.OrderItem;
//import com.hexaware.repository.OrderItemRepository;
//
//@Component
//public class OrderMapperImpl extends OrderMapper {
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private OrderItemRepository orderItemRepository;
//
//    @Override
//    public OrderDTO orderToOrderDto(Order order) {
//        OrderDTO dto = modelMapper.map(order, OrderDTO.class);
//
//        // Fetch order items
//        List<OrderItem> orderItems = orderItemRepository.findByOrderOrderId(order.getOrderId());
//
//        List<OrderItemDTO> itemDTOs = orderItems.stream()
//        	    .map(orderItem -> {
//        	        OrderItemDTO itemDTO = new OrderItemDTO();
//        	        itemDTO.setOrderItemId(orderItem.getOrderItemId());
//        	        itemDTO.setQuantity(orderItem.getQuantity());
//        	        itemDTO.setPrice(orderItem.getPrice());
//
//        	        Menu menu = orderItem.getMenu();
//        	        if (menu != null) {
//        	            MenuDTO menuDTO = modelMapper.map(menu, MenuDTO.class);
//        	            itemDTO.setMenu(menuDTO);
//        	        }
//
//        	        return itemDTO;
//        	    })
//        	    .collect(Collectors.toList());
//
//        	dto.setItems(itemDTOs);
//
//        return dto;
//    }
//}
