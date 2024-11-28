package com.Application1.in.mapper;

import java.util.List;
import java.util.Optional;

import com.Application1.in.DTO.UserDTO;
import com.Application1.in.Entity.User;

public interface UserMapper 
{

    public UserDTO userToUserDTO(User user);  // Convert User to UserDTO
    public List<UserDTO> usersToUserDTOs(List<User> users);//convert list of user to userDTO
    public User userDTOToUser(UserDTO userDTO);  // Convert UserDTO to User
}

