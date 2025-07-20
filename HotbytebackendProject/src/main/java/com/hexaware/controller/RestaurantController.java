package com.hexaware.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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



import com.hexaware.dto.RestaurantDTO;
import com.hexaware.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurant")
@CrossOrigin("http://localhost:3000")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // Restaurant Management APIs
    @PostMapping("/register")
    public ResponseEntity<RestaurantDTO> registerRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return ResponseEntity.ok(restaurantService.registerRestaurant(restaurantDTO));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable int restaurantId) {
        return ResponseEntity.ok(restaurantService.getRestaurant(restaurantId));
    }

    @GetMapping("/get")
    public ResponseEntity<RestaurantDTO> getRestaurantByUserName(Principal principal) {
        return ResponseEntity.ok(restaurantService.getRestaurantByUserName(principal.getName()));
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable int restaurantId, @RequestBody RestaurantDTO restaurantDTO) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantId, restaurantDTO));
    }
    @PutMapping("/{restaurantId}/activate")
    public ResponseEntity<RestaurantDTO> activateRestaurant(@PathVariable int restaurantId) {
        RestaurantDTO updated = restaurantService.updateRestaurantStatus(restaurantId, "ACTIVE");
        return ResponseEntity.ok(updated);
    }
    
    @PutMapping("/{restaurantId}/deactivate")
    public ResponseEntity<RestaurantDTO> deactivateRestaurant(@PathVariable int restaurantId) {
        RestaurantDTO updated = restaurantService.updateRestaurantStatus(restaurantId, "INACTIVE");
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable int restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }
    
    @PutMapping("/status")
    public ResponseEntity<RestaurantDTO> toggleRestaurantStatus(Principal principal, @RequestBody Map<String, Boolean> request) {
        String username = principal.getName();
        boolean open = request.get("open"); // true = ACTIVE, false = INACTIVE
        RestaurantDTO updated = restaurantService.toggleStatusByUsername(username, open);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/current")
    public ResponseEntity<RestaurantDTO> getCurrentRestaurant(Principal principal) {
        String username = principal.getName();
        return ResponseEntity.ok(restaurantService.getRestaurantByUserName(username));
    }



}