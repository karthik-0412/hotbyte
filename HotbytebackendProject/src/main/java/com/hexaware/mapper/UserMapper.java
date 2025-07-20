package com.hexaware.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.dto.UserCreateDTO;
import com.hexaware.dto.UserDTO;
import com.hexaware.entity.User;

@Component
public class UserMapper {
	
	@Autowired
    private ModelMapper modelMapper;

    public User userDtoToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO userToUserDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    
    public User userCreateDtoToUser(UserCreateDTO createDTO) {
        return modelMapper.map(createDTO, User.class);
    }

}
