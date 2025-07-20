package com.hexaware.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.entity.User;
import com.hexaware.repository.UserRepository;
import com.hexaware.enums.UserRole;
import com.hexaware.enums.UserStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//
//        // Map UserRole to Spring Security authority with "ROLE_" prefix
//        String role = "ROLE_" + user.getRole().name();
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                true, true, true, true, // enabled, accountNonExpired, credentialsNonExpired, accountNonLocked
//                Collections.singletonList(authority));
//    }
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// Fetch User by username
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		
		if (user.getStatus() == UserStatus.SUSPENDED) {
            throw new BadCredentialsException("Your account is suspended.");
        }
		
		user.setLastLogin(LocalDate.now());
	    userRepository.save(user);	
		
		if(user == null)
			throw new UsernameNotFoundException("Invalid Credentials");
		// Convert the Role as Spring Authority
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ROLE_" +user.getRole().name());
		
		
		// Our User convert to spring security User that is UserDetails
		org.springframework.security.core.userdetails.User springUser =
				new org.springframework.security.core.userdetails.User(
															user.getUsername(),
															user.getPassword(), 
															List.of(sga)); // List of users
		 
		return springUser;
	}
}