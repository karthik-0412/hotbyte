package com.hexaware.service;

import com.hexaware.dto.RegisterRequestDTO;

public interface UserService {
    String register(RegisterRequestDTO registerRequest);
}
