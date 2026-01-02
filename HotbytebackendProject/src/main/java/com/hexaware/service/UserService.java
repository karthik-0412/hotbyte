package com.hexaware.service;

import java.util.List;

import com.hexaware.dto.UserCreateDTO;
import com.hexaware.dto.UserDTO;
import com.hexaware.enums.UserStatus;

public interface UserService {
    UserDTO createUser(UserCreateDTO userCreateDTO);
    UserDTO getUserById(int userId);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(int userId, UserDTO userDTO);
    void deleteUser(int userId);
	UserDTO getUserByUserName(String username);
//	UserDTO createUser(UserCreateDTO userCreateDTO);
	void updateUserStatus(int userId, UserStatus status);
	boolean changePassword(String username, String oldPassword, String newPassword);
}