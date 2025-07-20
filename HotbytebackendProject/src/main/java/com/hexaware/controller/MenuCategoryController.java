package com.hexaware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.MenuCategoryDTO;
import com.hexaware.service.MenuCategoryService;
import com.hexaware.service.RestaurantService;
@RestController
@RequestMapping("api/category")
@CrossOrigin("http://localhost:3000")
public class MenuCategoryController {
	@Autowired
	private MenuCategoryService categoryService;
	@PostMapping("/add")
    public ResponseEntity<MenuCategoryDTO> addMenuCategory(@RequestBody MenuCategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.addMenuCategory(categoryDTO));
    }

    // Get all categories
    @GetMapping("/get")
    public ResponseEntity<List<MenuCategoryDTO>> getAllMenuCategories() {
        return ResponseEntity.ok(categoryService.getAllMenuCategories());
    }
    
    @GetMapping("/with-counts")
    public ResponseEntity<List<MenuCategoryDTO>> getCategoriesWithCounts() {
        return ResponseEntity.ok(categoryService.getAllCategoriesWithItemCounts());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MenuCategoryDTO> updateMenuCategory(@PathVariable int id,
                                                              @RequestBody MenuCategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateMenuCategory(id, categoryDTO));
    }

    // Delete Category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuCategory(@PathVariable int id) {
        categoryService.deleteMenuCategory(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}/activate")
    public ResponseEntity<MenuCategoryDTO> activateCategory(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.changeStatus(id, "Active"));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<MenuCategoryDTO> deactivateCategory(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.changeStatus(id, "Inactive"));
    }


}
