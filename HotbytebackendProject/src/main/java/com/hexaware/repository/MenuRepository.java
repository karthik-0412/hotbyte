package com.hexaware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.dto.PopularItemDTO;
import com.hexaware.entity.Menu;
import com.hexaware.entity.MenuCategory;
import com.hexaware.entity.Restaurant;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
	List<Menu> findByRestaurantRestaurantId(int restaurantId);

	@Query("SELECT COUNT(m) FROM Menu m WHERE m.category = :category")
	int countByCategory(@Param("category") MenuCategory category);


	void deleteByRestaurant(Restaurant restaurant);

	@Query("SELECT new com.hexaware.dto.PopularItemDTO(" +
		       "m.itemName, m.category.categoryName, m.price, m.cookingTime, m.imageUrl) " +
		       "FROM Menu m WHERE m.restaurant.restaurantId = :restaurantId " +
		       "ORDER BY m.rating DESC")
	List<PopularItemDTO> findPopularItemsByRestaurantId(@Param("restaurantId") int restaurantId);



	int countByRestaurant(Restaurant restaurant);

//	int countByRestaurant(Restaurant restaurant);
}
