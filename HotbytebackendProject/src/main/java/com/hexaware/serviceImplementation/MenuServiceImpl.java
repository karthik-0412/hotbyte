package com.hexaware.serviceImplementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.MenuDTO;
import com.hexaware.dto.MenuFilterDto;
import com.hexaware.entity.Menu;
import com.hexaware.entity.MenuCategory;
import com.hexaware.entity.Restaurant;
import com.hexaware.mapper.MenuMapper;
import com.hexaware.repository.MenuCategoryRepository;
import com.hexaware.repository.MenuRepository;
import com.hexaware.repository.RestaurantRepository;
import com.hexaware.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService{
	// Menu Management
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private MenuCategoryRepository menuCategoryRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public MenuDTO addMenuItem(MenuDTO menuDTO) {
	    Menu menu = menuMapper.menuDtoToMenu(menuDTO);

	    // Fetch restaurant by ID
	    if (menuDTO.getRestaurant() != null && menuDTO.getRestaurant().getRestaurantId() > 0) {
	        Restaurant restaurant = restaurantRepository.findById(menuDTO.getRestaurant().getRestaurantId())
	                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
	        menu.setRestaurant(restaurant);
	    } else {
	        throw new RuntimeException("Restaurant is required");
	    }

	    // Fetch category by ID or name
	    if (menuDTO.getCategory() != null && menuDTO.getCategory().getCategoryId() > 0) {
	        MenuCategory category = menuCategoryRepository.findById(menuDTO.getCategory().getCategoryId())
	                .orElseThrow(() -> new RuntimeException("Category not found"));
	        menu.setCategory(category);
	    } else if (menuDTO.getCategory() != null && menuDTO.getCategory().getCategoryName() != null) {
	        MenuCategory category = menuCategoryRepository.findByCategoryName(menuDTO.getCategory().getCategoryName())
	                .orElseThrow(() -> new RuntimeException("Category not found with name: " + menuDTO.getCategory().getCategoryName()));
	        menu.setCategory(category);
	    } else {
	        throw new RuntimeException("Category is required");
	    }
	    System.out.println("Received MenuDTO: " + menuDTO);
	    System.out.println("Category: " + (menuDTO.getCategory() != null ? menuDTO.getCategory().getCategoryName() : "null"));
	    System.out.println("Restaurant ID: " + (menuDTO.getRestaurant() != null ? menuDTO.getRestaurant().getRestaurantId() : "null"));

	    menu = menuRepository.save(menu);
	    return menuMapper.menuToMenuDto(menu);

	}



    @Override
    public MenuDTO updateMenuItem(int id, MenuDTO menuDTO) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        menu.setItemName(menuDTO.getItemName());
        menu.setDescription(menuDTO.getDescription());
        menu.setPrice(menuDTO.getPrice());
        menu.setOriginalPrice(menuDTO.getOriginalPrice());
        menu.setOffer(menuDTO.getOffer());
        menu.setRating(menuDTO.getRating());
        menu.setImageUrl(menuDTO.getImageUrl());
        menu.setDietaryInfo(menuDTO.getDietaryInfo());
        menu.setTasteInfo(menuDTO.getTasteInfo());
        menu.setCookingTime(menuDTO.getCookingTime());
        menu.setAvailability(menuDTO.getAvailability());
        menu.setCalories(menuDTO.getCalories());
        menu.setProteins(menuDTO.getProteins());
        menu.setFats(menuDTO.getFats());
        menu.setCarbs(menuDTO.getCarbs());
        menu.setIngredients(menuDTO.getIngredients());
        menu.setLocation(menuDTO.getLocation());
        menu.setAvailabilityTime(menuDTO.getAvailabilityTime());
        menu.setAddedOn(menuDTO.getAddedOn());

        if (menuDTO.getRestaurant() != null && menuDTO.getRestaurant().getRestaurantId() > 0) {
            Restaurant restaurant = restaurantRepository.findById(menuDTO.getRestaurant().getRestaurantId())
                    .orElseThrow(() -> new RuntimeException("Restaurant not found"));
            menu.setRestaurant(restaurant);
        }

        if (menuDTO.getCategory() != null && menuDTO.getCategory().getCategoryId() > 0) {
            MenuCategory category = menuCategoryRepository.findById(menuDTO.getCategory().getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            menu.setCategory(category);
        }

        Menu updated = menuRepository.save(menu);
        return menuMapper.menuToMenuDto(updated);
    }



    @Override
    public void deleteMenuItem(int id) {
        if (!menuRepository.existsById(id)) {
            throw new RuntimeException("Menu item not found with ID: " + id);
        }

        try {
            menuRepository.deleteById(id);
            System.out.println("Successfully deleted menu item with ID: " + id);
        } catch (Exception e) {
            e.printStackTrace(); // 👈 Log full stack trace
            throw new RuntimeException("Failed to delete menu item: " + e.getMessage());
        }
    }
    
    @Override
    public void toggleAvailability(int menuId) {
        System.out.println("Toggling availability for menuId: " + menuId);
        Menu menu = menuRepository.findById(menuId)
            .orElseThrow(() -> new RuntimeException("Menu item not found"));

        if (menu.getAvailability() == null || menu.getAvailability().equalsIgnoreCase("unavailable")) {
            menu.setAvailability("available");
        } else {
            menu.setAvailability("unavailable");
        }

        menuRepository.save(menu);
    }


 



    @Override
    public List<MenuDTO> viewAllMenus() {
        // Assuming current restaurant context, replace with actual logic
//        List<Menu> menus = menuRepository.findByRestaurantRestaurantId(1); // Placeholder
    	List<Menu> menus = menuRepository.findAll();
        return menus.stream()
                .map(menuMapper::menuToMenuDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<MenuDTO> browseMenu() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream()
                .map(menuMapper::menuToMenuDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<MenuDTO> filterMenu(MenuFilterDto filter) {
//        List<Menu> menus = menuRepository.findAll();
//        return menus.stream()
//                .filter(menu -> (filter.getCategory() == null || menu.getCategory().getCategoryName().equals(filter.getCategory())))
//                .filter(menu -> (filter.getFoodType() == null || menu.getCategory().getFoodType().name().equals(filter.getFoodType())))
//                .filter(menu -> (filter.getMaxPrice() == null || menu.getPrice().compareTo(BigDecimal.valueOf(filter.getMaxPrice())) <= 0))
//                .map(menuMapper::menuToMenuDto)
//                .collect(Collectors.toList());
//    }
}
