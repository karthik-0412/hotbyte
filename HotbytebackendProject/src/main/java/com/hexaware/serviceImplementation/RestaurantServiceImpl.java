package com.hexaware.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.dto.AddressDTO;
import com.hexaware.dto.CustomerDTO;
import com.hexaware.dto.MenuCategoryDTO;
import com.hexaware.dto.MenuDTO;
import com.hexaware.dto.OrderDTO;
import com.hexaware.dto.RestaurantDTO;
import com.hexaware.dto.UserCreateDTO;
import com.hexaware.dto.UserDTO;
import com.hexaware.entity.Address;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Menu;
import com.hexaware.entity.MenuCategory;
import com.hexaware.entity.Order;
import com.hexaware.entity.Restaurant;
import com.hexaware.entity.User;
import com.hexaware.enums.OrderStatus;
import com.hexaware.enums.RestaurantStatus;
import com.hexaware.enums.UserRole;
import com.hexaware.enums.UserStatus;
import com.hexaware.exceptions.ResourceNotFoundException;
import com.hexaware.mapper.AddressMapper;
import com.hexaware.mapper.MenuCategoryMapper;
import com.hexaware.mapper.MenuMapper;
import com.hexaware.mapper.OrderMapper;
import com.hexaware.mapper.RestaurantMapper;
import com.hexaware.mapper.UserMapper;
import com.hexaware.repository.AddressRepository;
import com.hexaware.repository.MenuCategoryRepository;
import com.hexaware.repository.MenuRepository;
import com.hexaware.repository.OrderRepository;
import com.hexaware.repository.RestaurantRepository;
import com.hexaware.repository.UserRepository;
import com.hexaware.service.RestaurantService;
import com.hexaware.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	private UserService userService;


    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MenuCategoryRepository categoryRepository;

    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuCategoryMapper categoryMapper;
    
    @Autowired
    private AddressMapper addressMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    // Restaurant Management
    @Transactional
    @Override
    public RestaurantDTO registerRestaurant(RestaurantDTO restaurantDTO) {
        try {
            System.out.println("Received restaurantDTO: " + restaurantDTO);

            // Step 1: Build UserCreateDTO
            UserCreateDTO userCreateDTO = new UserCreateDTO();
            userCreateDTO.setUsername(restaurantDTO.getUserdto().getUsername());
            userCreateDTO.setName(restaurantDTO.getUserdto().getName());
            userCreateDTO.setEmail(restaurantDTO.getUserdto().getEmail());
            userCreateDTO.setPhoneNumber(restaurantDTO.getUserdto().getPhoneNumber());
            userCreateDTO.setRole(UserRole.RESTAURANT);
            userCreateDTO.setStatus(UserStatus.ACTIVE);
            userCreateDTO.setPassword(restaurantDTO.getUserdto().getPassword());

//            System.out.println("Creating user...");
            UserDTO createdUserDTO = userService.createUser(userCreateDTO);
//            System.out.println("User created: " + createdUserDTO);

            User user = userRepository.findById(createdUserDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User creation failed"));

            // Step 3: Create address
            Address address = addressMapper.addressDtoToAddress(restaurantDTO.getAddressdto());
            address = addressRepository.save(address);

            // Remove embedded user/address to avoid ModelMapper confusion
            restaurantDTO.setUserdto(null);
            restaurantDTO.setAddressdto(null);

            // Step 4: Map and enrich
            Restaurant restaurant = restaurantMapper.restaurantDtoToRestaurant(restaurantDTO);
            restaurant.setUser(user);
            restaurant.setAddress(address);

            try {
                restaurant.setStatus(RestaurantStatus.valueOf(restaurantDTO.getStatus().toUpperCase()));
            } catch (Exception e) {
                restaurant.setStatus(RestaurantStatus.INACTIVE);
            }

            restaurant.setRating(restaurantDTO.getRating() != null ? restaurantDTO.getRating() : 0.0);

            // Step 5: Save
            restaurant = restaurantRepository.save(restaurant);
//            System.out.println("Restaurant saved: " + restaurant.getRestaurantName());

            return restaurantMapper.restaurantToRestaurantDto(restaurant);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Restaurant registration failed: " + ex.getMessage());
        }
    }






    @Override
    public RestaurantDTO getRestaurant(int restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
        return restaurantMapper.restaurantToRestaurantDto(restaurant);
    }

    @Override
    public RestaurantDTO updateRestaurant(int restaurantId, RestaurantDTO restaurantDTO) {
        // Fetch the existing restaurant
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new RuntimeException("Restaurant not found with ID: " + restaurantId));

        // Update fields if present in DTO
        if (restaurantDTO.getRestaurantName() != null) {
            restaurant.setRestaurantName(restaurantDTO.getRestaurantName());
        }
        if (restaurantDTO.getLocation() != null) {
            restaurant.setLocation(restaurantDTO.getLocation());
        }
        if (restaurantDTO.getDescription() != null) {
            restaurant.setDescription(restaurantDTO.getDescription());
        }
        if (restaurantDTO.getRating() != null) {
            restaurant.setRating(restaurantDTO.getRating());
        }
        if (restaurantDTO.getStatus() != null) {
            restaurant.setStatus(RestaurantStatus.valueOf(restaurantDTO.getStatus().toUpperCase()));
        }

        // ✅ Correctly handle Address update
        if (restaurantDTO.getAddressdto() != null && restaurantDTO.getAddressdto().getAddressId() != 0) {
            Address existingAddress = addressRepository.findById(restaurantDTO.getAddressdto().getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found with ID: " + restaurantDTO.getAddressdto().getAddressId()));

            existingAddress.setStreet(restaurantDTO.getAddressdto().getStreet());
            existingAddress.setCity(restaurantDTO.getAddressdto().getCity());
            existingAddress.setState(restaurantDTO.getAddressdto().getState());
            existingAddress.setPincode(restaurantDTO.getAddressdto().getPincode());

            restaurant.setAddress(existingAddress);
        }

        // ✅ Optionally update user info
        if (restaurantDTO.getUserdto() != null) {
            User user = restaurant.getUser();
            UserCreateDTO userDto = restaurantDTO.getUserdto();

            if (userDto.getPhoneNumber() != null) {
                user.setPhoneNumber(userDto.getPhoneNumber());
            }
            if (userDto.getName() != null) {
                user.setName(userDto.getName());
            }

            restaurant.setUser(user);
        }

        // Save and return updated restaurant
        restaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.restaurantToRestaurantDto(restaurant);
    }




    @Transactional
    public void deleteRestaurant(int restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        // Manually delete related menu items
        menuRepository.deleteByRestaurant(restaurant);

        restaurantRepository.delete(restaurant);
    }
    
    @Override
    public RestaurantDTO toggleStatusByUsername(String username, boolean open) {
        Restaurant restaurant = restaurantRepository.findByUserUsername(username);
        if (restaurant == null) {
            throw new ResourceNotFoundException("Restaurant not found");
        }

        restaurant.setStatus(open ? RestaurantStatus.ACTIVE : RestaurantStatus.INACTIVE);
        Restaurant updated = restaurantRepository.save(restaurant);
        return modelMapper.map(updated, RestaurantDTO.class);
    }



    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurantMapper::restaurantToRestaurantDto)
                .collect(Collectors.toList());
    }


//    @Override
//	public CustomerDTO getCustomerByUserName(String username) {
//		Customer customer = customerRepository.findByUsername(username);
//		return customerMapper.customerToCustomerDto(customer);
//	}

	@Override
	public RestaurantDTO getRestaurantByUserName(String username) {
		Restaurant restaurant = restaurantRepository.findByUserUsername(username);
        return restaurantMapper.restaurantToRestaurantDto(restaurant);
	}




	@Override
	public RestaurantDTO updateRestaurantStatus(int restaurantId, String status) {
	    Restaurant restaurant = restaurantRepository.findById(restaurantId)
	        .orElseThrow(() -> new RuntimeException("Restaurant not found with ID: " + restaurantId));

	    try {
	        restaurant.setStatus(RestaurantStatus.valueOf(status.toUpperCase()));
	    } catch (IllegalArgumentException e) {
	        throw new RuntimeException("Invalid status: " + status);
	    }

	    restaurant = restaurantRepository.save(restaurant);
	    return restaurantMapper.restaurantToRestaurantDto(restaurant);
	}




}