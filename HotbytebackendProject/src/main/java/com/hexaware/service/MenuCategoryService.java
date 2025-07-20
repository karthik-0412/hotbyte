package com.hexaware.service;

import java.util.List;

import com.hexaware.dto.MenuCategoryDTO;

public interface MenuCategoryService {
	//MenuCategoryManagement
	MenuCategoryDTO addMenuCategory(MenuCategoryDTO categoryDTO);

    List<MenuCategoryDTO> getAllMenuCategories();

    List<MenuCategoryDTO> getAllCategoriesWithItemCounts();

    MenuCategoryDTO updateMenuCategory(int id, MenuCategoryDTO categoryDTO);

    void deleteMenuCategory(int id);
    
    MenuCategoryDTO changeStatus(int id, String status);

}
