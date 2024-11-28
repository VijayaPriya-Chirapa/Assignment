package com.Application1.in.service;

import java.util.List;
import java.util.Optional;

import com.Application1.in.DTO.UserDTO;
import com.Application1.in.Entity.User;

public interface UserService 
{
	public List<UserDTO> getAllUsers();
	public UserDTO addUser(User user); 

}

