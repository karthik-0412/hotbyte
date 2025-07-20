package com.hexaware.serviceImplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.MenuCategoryDTO;
import com.hexaware.entity.MenuCategory;
import com.hexaware.exceptions.ResourceNotFoundException;
import com.hexaware.mapper.MenuCategoryMapper;
import com.hexaware.repository.MenuCategoryRepository;
import com.hexaware.repository.MenuRepository;
import com.hexaware.service.MenuCategoryService;

@Service
public class MenuCategoryServiceImpl implements MenuCategoryService {

    @Autowired
    private MenuCategoryMapper categoryMapper;

    @Autowired
    private MenuCategoryRepository categoryRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public MenuCategoryDTO addMenuCategory(MenuCategoryDTO categoryDTO) {
        MenuCategory category = categoryMapper.dtoToEntity(categoryDTO);
        
        category.setStatus("Active"); // Set default status
        category.setCreatedAt(LocalDate.now());
        category = categoryRepository.save(category);
        return categoryMapper.entityToDto(category);
    }

    @Override
    public List<MenuCategoryDTO> getAllMenuCategories() {
        List<MenuCategory> categories = categoryRepository.findAll();
        return categories.stream()
                         .map(categoryMapper::entityToDto)
                         .collect(Collectors.toList());
    }

    @Override
    public List<MenuCategoryDTO> getAllCategoriesWithItemCounts() {
        List<MenuCategory> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> {
                    MenuCategoryDTO dto = categoryMapper.entityToDto(category);
                    int count = menuRepository.countByCategory(category);
                    dto.setItemCount(count);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public MenuCategoryDTO updateMenuCategory(int id, MenuCategoryDTO categoryDTO) {
        MenuCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));

        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());

        return categoryMapper.entityToDto(categoryRepository.save(category));
    }

    @Override
    public void deleteMenuCategory(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }
    
    @Override
    public MenuCategoryDTO changeStatus(int id, String status) {
        MenuCategory category = categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        category.setStatus(status);
        return categoryMapper.entityToDto(categoryRepository.save(category));

    }

}
