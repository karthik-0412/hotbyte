package com.hexaware.service;

import java.util.List;

import com.hexaware.dto.MenuDTO;
import com.hexaware.dto.MenuFilterDto;

public interface MenuService {
	// Menu Management
    MenuDTO addMenuItem(MenuDTO menuDTO);
    MenuDTO updateMenuItem(int id, MenuDTO menuDTO);
    void deleteMenuItem(int id);
    List<MenuDTO> viewAllMenus();
    List<MenuDTO> browseMenu();
//    List<MenuDTO> filterMenu(MenuFilterDto filter);
	void toggleAvailability(int menuId);

}
