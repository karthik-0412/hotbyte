package com.hexaware.serviceImplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.dto.UserCreateDTO;
import com.hexaware.dto.UserDTO;
import com.hexaware.entity.User;
import com.hexaware.enums.UserRole;
import com.hexaware.enums.UserStatus;
import com.hexaware.exceptions.ResourceNotFoundException;
import com.hexaware.exceptions.UserAlreadyExistsException;
import com.hexaware.exceptions.UserNotFoundException;
import com.hexaware.mapper.UserMapper;
import com.hexaware.repository.CustomerRepository;
import com.hexaware.repository.OrderRepository;
import com.hexaware.repository.RestaurantRepository;
import com.hexaware.repository.UserRepository;
import com.hexaware.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private CustomerRepository customerRepository;


    @Transactional
    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        if (userRepository.existsByEmail(userCreateDTO.getEmail())) {
            throw new UserAlreadyExistsException("Email already in use");
        }

        if (userCreateDTO.getPassword() == null || userCreateDTO.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        User user = userMapper.userCreateDtoToUser(userCreateDTO);
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setMemberSince(LocalDate.now());

        // ✅ Directly set enums from DTO
        user.setStatus(userCreateDTO.getStatus() != null ? userCreateDTO.getStatus() : UserStatus.ACTIVE);
        user.setRole(userCreateDTO.getRole() != null ? userCreateDTO.getRole() : UserRole.CUSTOMER);
        
        if (user.getUsername() == null) {
            user.setUsername(userCreateDTO.getEmail());
        }

        User savedUser = userRepository.save(user);

        UserDTO userDTO = userMapper.userToUserDto(savedUser);
        userDTO.setOrders(0); // if needed
        userDTO.setStatus(savedUser.getStatus()); // if DTO expects string
        userDTO.setRole(savedUser.getRole());     // if DTO expects string

        return userDTO;
    }





    @Override
    public UserDTO getUserById(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
            .map(user -> {
                UserDTO dto = userMapper.userToUserDto(user);
                int orderCount = orderRepository.countByCustomer_User_UserId(user.getUserId()); // assuming relation
                dto.setOrders(orderCount);
                return dto;
            })
            .collect(Collectors.toList());
    }
    @Override
    public UserDTO updateUser(int userId, UserDTO userDTO) {
        User existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setRole(userDTO.getRole());
        existingUser.setStatus(userDTO.getStatus());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.userToUserDto(updatedUser);
    }


    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        userRepository.delete(user);
    }


	
	@Override
    public void updateUserStatus(int userId, UserStatus status) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        user.setStatus(status);
        userRepository.save(user);
    }
	
	@Override
	public UserDTO getUserByUserName(String username) {
	    User user = userRepository.findByUsername(username).orElse(null);
	    if (user == null) {
	        throw new ResourceNotFoundException("User not found with username: " + username);
	    }

	    UserDTO dto = userMapper.userToUserDto(user);

	    // Add restaurantId if user is a RESTAURANT
	    if (user.getRole() == UserRole.RESTAURANT) {
	        restaurantRepository.findByUser(user).ifPresent(restaurant ->
	            dto.setRestaurantId(restaurant.getRestaurantId())
	        );
	    }
	    if (user.getRole() == UserRole.CUSTOMER) {
	        customerRepository.findByUser(user).ifPresent(customer ->
	            dto.setCustomerId(customer.getCustomerId())
	        );
	    }
	    return dto;
	}



	
	
}