package com.hexaware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.entity.Menu;
import com.hexaware.entity.MenuCategory;
import com.hexaware.entity.Restaurant;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
	List<Menu> findByRestaurantRestaurantId(int restaurantId);

    int countByCategory(MenuCategory category);

	void deleteByRestaurant(Restaurant restaurant);
}
