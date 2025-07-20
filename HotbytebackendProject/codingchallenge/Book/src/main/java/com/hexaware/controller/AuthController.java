package com.hexaware.controller;

import com.hexaware.dto.AuthRequestDTO;
import com.hexaware.dto.RegisterRequestDTO;
import com.hexaware.util.JwtUtil;
import com.hexaware.entity.User;
import com.hexaware.repository.UserRepository;
import com.hexaware.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public String register(@RequestBody RegisterRequestDTO registerRequest) {
	    return userService.register(registerRequest);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AuthRequestDTO authRequest) {
	    try {
	        authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	    } catch (AuthenticationException ex) {
	        throw new RuntimeException("Invalid username or password");
	    }

	    User user = userRepository.findByUsername(authRequest.getUsername())
	        .orElseThrow(() -> new RuntimeException("User not found"));

	    String token = jwtUtil.generateToken(user.getUsername());

	    return ResponseEntity.ok(token); // <--- this returns the token
	}

}
