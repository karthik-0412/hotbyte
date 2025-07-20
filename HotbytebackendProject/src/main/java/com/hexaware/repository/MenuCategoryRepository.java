package com.hexaware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.entity.MenuCategory;
import com.hexaware.entity.Restaurant;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Integer>{

	Optional<MenuCategory> findByCategoryName(String categoryName);


}
