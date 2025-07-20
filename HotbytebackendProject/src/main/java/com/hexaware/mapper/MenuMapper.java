package com.hexaware.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hexaware.dto.MenuDTO;
import com.hexaware.dto.MenuCategoryDTO;
import com.hexaware.dto.RestaurantDTO;
import com.hexaware.entity.Menu;
import com.hexaware.entity.MenuCategory;
import com.hexaware.entity.Restaurant;
import org.modelmapper.ModelMapper;

@Component
public class MenuMapper {

    @Autowired
    private ModelMapper modelMapper;

    public MenuDTO menuToMenuDto(Menu menu) {
        MenuDTO dto = modelMapper.map(menu, MenuDTO.class);

        // Nested mapping
        if (menu.getRestaurant() != null) {
            dto.setRestaurant(modelMapper.map(menu.getRestaurant(), RestaurantDTO.class));
        }
        if (menu.getCategory() != null) {
            dto.setCategory(modelMapper.map(menu.getCategory(), MenuCategoryDTO.class));
        }

        return dto;
    }

    public Menu menuDtoToMenu(MenuDTO dto) {
        Menu menu = modelMapper.map(dto, Menu.class);

        if (dto.getRestaurant() != null && dto.getRestaurant().getRestaurantId() > 0) {
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(dto.getRestaurant().getRestaurantId());
            menu.setRestaurant(restaurant);
        }

        if (dto.getCategory() != null && dto.getCategory().getCategoryId() > 0) {
            MenuCategory category = new MenuCategory();
            category.setCategoryId(dto.getCategory().getCategoryId());
            menu.setCategory(category);
        }

        return menu;
    }
}
