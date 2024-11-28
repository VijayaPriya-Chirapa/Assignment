package com.Application1.in.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.Application1.in.DTO.UserDTO;
import com.Application1.in.Entity.User;

@Component 
public class UserMappperImp implements UserMapper {
	 
	    @Override
	    public UserDTO userToUserDTO(User user) 
	    {
	        if (user == null) 
	        {
	            return null;
	        }
	        UserDTO userDto = new UserDTO();
	        userDto.setId(user.getId());
	        userDto.setName(user.getName());
	        userDto.setEmail(user.getEmail());
	        return userDto;
	    }
	 
	    @Override
	    public User userDTOToUser(UserDTO userDto) {

	        if (userDto == null) 
	        {
	            return null;
	        }
	    
	        User user = new User();
	        user.setId(userDto.getId());
	        user.setName(userDto.getName());
	        user.setEmail(userDto.getEmail());
	        return user;

	    }
	    
	    
	    @Override
	    public List<UserDTO> usersToUserDTOs(List<User> users) 
	    
	    {
	        if (users == null || users.isEmpty()) {
	            return null;
	        }
	        List<UserDTO> userDTOs = new ArrayList<>();
	        for (User user : users) {
	            UserDTO userDTO = userToUserDTO(user);  // Convert each User to UserDTO using the existing method
	            userDTOs.add(userDTO);  // Add the converted UserDTO to the list
	        }

	        return userDTOs;  // Return the list of UserDTOs
	    }


	}
	 
	 