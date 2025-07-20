package com.hexaware.serviceImplementation;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.javapoet.ClassName;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hexaware.dto.AddressDTO;
import com.hexaware.dto.CartDTO;
import com.hexaware.dto.CartRefDTO;
import com.hexaware.dto.OrderCreateDTO;
import com.hexaware.dto.OrderDTO;
import com.hexaware.entity.Address;
import com.hexaware.entity.Cart;
import com.hexaware.entity.Coupon;
//import com.hexaware.entity.CartItem;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Menu;
import com.hexaware.entity.Order;
import com.hexaware.entity.Payment;
import com.hexaware.entity.Restaurant;
import com.hexaware.entity.OrderItem;
import com.hexaware.enums.OrderStatus;
import com.hexaware.enums.PaymentStatus;
import com.hexaware.exceptions.DataIntegrityViolationException;
import com.hexaware.exceptions.ResourceNotFoundException;
import com.hexaware.mapper.AddressMapper;
import com.hexaware.mapper.OrderMapper;
import com.hexaware.repository.AddressRepository;
//import com.hexaware.repository.CartItemRepository;
import com.hexaware.repository.CartRepository;
import com.hexaware.repository.CouponRepository;
import com.hexaware.repository.CustomerRepository;
import com.hexaware.repository.MenuRepository;
import com.hexaware.repository.OrderItemRepository;
import com.hexaware.repository.OrderRepository;
import com.hexaware.repository.PaymentRepository;
import com.hexaware.repository.RestaurantRepository;
import com.hexaware.service.EmailService;
import com.hexaware.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger log = LoggerFactory.getLogger(ClassName.class);
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private CouponRepository couponRepository;
    

    @Override
    public OrderDTO placeOrder(OrderCreateDTO dto, String username, int restaurantId) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found");
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one cart item");
        }

        List<Integer> cartIds = dto.getItems().stream()
            .map(CartRefDTO::getCartId)
            .collect(Collectors.toList());

        List<Cart> cartItems = cartRepository.findAllById(cartIds);

        if (cartItems.size() != cartIds.size()) {
            throw new IllegalStateException("Some cart items are invalid");
        }

        for (Cart cart : cartItems) {
            if (cart.getCustomer().getCustomerId() != customer.getCustomerId() ||
                cart.getMenu().getRestaurant().getRestaurantId() != restaurantId) {
                throw new IllegalStateException("Cart item does not belong to customer or restaurant");
            }
        }

        // Save address
        Address address = new Address();
        address.setStreet(dto.getAddress().getStreet());
        address.setCity(dto.getAddress().getCity());
        address.setState(dto.getAddress().getState());
        address.setPincode(dto.getAddress().getPincode());
        address = addressRepository.save(address);

        // Calculate total
        BigDecimal subtotal = BigDecimal.ZERO;
        int totalQuantity = 0;

        for (Cart cart : cartItems) {
            subtotal = subtotal.add(cart.getTotal());
            totalQuantity += cart.getQuantity();
        }

        BigDecimal discountAmount = BigDecimal.ZERO;
        Coupon coupon = null;

        if (dto.getCouponCode() != null && !dto.getCouponCode().isEmpty()) {
            coupon = couponRepository.findByCode(dto.getCouponCode());
            if (coupon == null || !coupon.getStatus().name().equals("ACTIVE")) {
                throw new IllegalArgumentException("Invalid or inactive coupon");
            }

            if (subtotal.doubleValue() >= coupon.getMinOrder()) {
                BigDecimal rawDiscount = subtotal.multiply(BigDecimal.valueOf(coupon.getDiscount()).divide(BigDecimal.valueOf(100)));
                BigDecimal maxAllowed = BigDecimal.valueOf(coupon.getMaxDiscount());
                discountAmount = rawDiscount.min(maxAllowed);

                // Increment usage
                coupon.setUsageCount(coupon.getUsageCount() + 1);
                couponRepository.save(coupon);
            }
        }

        BigDecimal tax = dto.getTax() != null ? dto.getTax() : BigDecimal.ZERO;
        BigDecimal deliveryFee = dto.getDeliveryFee() != null ? dto.getDeliveryFee() : BigDecimal.ZERO;

        BigDecimal totalPrice = subtotal.subtract(discountAmount).add(tax).add(deliveryFee);

        // Save order
        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setQuantity(totalQuantity);
        order.setSubtotal(subtotal);
        order.setDiscount(discountAmount);
        order.setTax(tax);
        order.setDeliveryFee(deliveryFee);
        order.setTotalPrice(totalPrice);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PLACED);
        order.setAddress(address);
        order.setEstimatedDelivery("30-45 mins");
        if (coupon != null) {
            order.setCoupon(coupon);
        }
        order = orderRepository.save(order);

        // Save OrderItems
        List<OrderItem> orderItems = new ArrayList<>();
        for (Cart cart : cartItems) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setMenu(cart.getMenu());
            item.setQuantity(cart.getQuantity());
            item.setPrice(cart.getTotal());
            orderItems.add(item);
        }
        orderItemRepository.saveAll(orderItems);

        // Save payment
        Payment payment = new Payment();
        payment.setAmount(totalPrice);
        payment.setMethod(dto.getPaymentMethod());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaymentDateTime(LocalDateTime.now());
        payment = paymentRepository.save(payment);

        order.setPayment(payment);
        order = orderRepository.save(order); // Update with payment

        // Delete cart items
        cartRepository.deleteAll(cartItems);

        return orderMapper.orderToOrderDto(order);
    }





    	
    @Override
    public List<OrderDTO> getOrderHistory(int customerId) {
        List<Order> orders = orderRepository.findByCustomerCustomerId(customerId);
        return orders.stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }
    
    
    @Override
    public List<OrderDTO> getOrdersByCustomerUsername(String username) {
        Customer customer = customerRepository.findByUserEmail(username)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Order> orders = orderRepository.findByCustomer(customer);

        return orders.stream()
            .map(orderMapper::orderToOrderDto)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<OrderDTO> getOrdersByRestaurant(int restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        List<Order> orders = orderRepository.findByRestaurant(restaurant);
        return orders.stream()
            .map(orderMapper::orderToOrderDto)
            .collect(Collectors.toList());
    }



    @Override
    public List<OrderDTO> viewOrders() {
        // Assuming current restaurant context, replace with actual logic
        List<Order> orders = orderRepository.findAll(); // Placeholder
        return orders.stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<OrderDTO> viewOrders(String username) {
        Restaurant restaurant = restaurantRepository.findByUserEmail(username)
            .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        List<Order> orders = orderRepository.findByRestaurantOrderByOrderDateDesc(restaurant);

        return orders.stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }


    @Override
    public OrderDTO updateOrderStatus(int id, OrderStatus status) {
        List<Order> orders = orderRepository.findByOrderId(id);
        if (orders.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        Order order = orders.get(0); // Since orderId is unique, take the first
        order.setStatus(status);
        order = orderRepository.save(order);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public OrderDTO getOrderById(int id) {
        List<Order> orders = orderRepository.findByOrderId(id);
        if (orders.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        Order order = orders.get(0); // Since orderId is unique, take the first
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public OrderDTO cancelOrder(int id) {
        List<Order> orders = orderRepository.findByOrderId(id);
        if (orders.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        Order order = orders.get(0); // Since orderId is unique, take the first
        order.setStatus(OrderStatus.CANCELLED);
        order = orderRepository.save(order);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public OrderDTO trackOrder(int id) {
        List<Order> orders = orderRepository.findByOrderId(id);
        if (orders.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        Order order = orders.get(0); // Since orderId is unique, take the first
        return orderMapper.orderToOrderDto(order);
    }
}