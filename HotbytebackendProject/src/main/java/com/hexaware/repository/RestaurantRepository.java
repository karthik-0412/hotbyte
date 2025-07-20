package com.hexaware.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.entity.Restaurant;
import com.hexaware.entity.User;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Restaurant findByUserUserId(int userId);
    List<Restaurant> findByStatus(String status);	
    Restaurant findByUserUsername(String username);
    Optional<Restaurant> findByUser(User user);
//    Restaurant findByUserUsername(String username);
	Optional<Restaurant> findByUserEmail(String username);



}
