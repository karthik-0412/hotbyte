package com.hexaware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.hexaware.dto.MenuDTO;
import com.hexaware.service.MenuService;

@RestController
@RequestMapping("api/menu")
@CrossOrigin("http://localhost:3000")
public class MenuController {

	@Autowired
	private MenuService menuService;
	// Menu Management APIs
    @PostMapping("/add")
    public ResponseEntity<MenuDTO> addMenuItem(@RequestBody MenuDTO menuDTO) {
        return ResponseEntity.ok(menuService.addMenuItem(menuDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MenuDTO> updateMenuItem(@PathVariable int id, @RequestBody MenuDTO menuDTO) {
        return ResponseEntity.ok(menuService.updateMenuItem(id, menuDTO));
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable int id) {
    	menuService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/menu")
    public ResponseEntity<List<MenuDTO>> browseMenu() {
        return ResponseEntity.ok(menuService.browseMenu());
    }
    
    @PutMapping("/{menuId}/toggleAvailability")
    public ResponseEntity<String> toggleAvailability(@PathVariable int menuId) {
        try {
            menuService.toggleAvailability(menuId);
            return ResponseEntity.ok("Availability toggled successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



 // View All Menus
    @GetMapping("/menus")
    public ResponseEntity<List<MenuDTO>> viewAllMenus() {
        return ResponseEntity.ok(menuService.viewAllMenus());
    }

    
}
