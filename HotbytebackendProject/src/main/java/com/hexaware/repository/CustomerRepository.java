package com.hexaware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.entity.Customer;
import com.hexaware.entity.Restaurant;
import com.hexaware.entity.User;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByUserUserId(int userId);
    @Query("select c from Customer c where c.user.username=?1")
	Customer findByUsername(String username);
	Optional<Customer> findByUser(User user);
	Optional<Customer> findByUserEmail(String username);

}
