package com.Application1.in.BO;

import java.util.List;
import java.util.Optional;

import com.Application1.in.DTO.UserDTO;
import com.Application1.in.Entity.User;

public interface UserBO
{
	 public UserDTO getUserById(Long id);
	 public  UserDTO addUser(User user);
	 public List<UserDTO> getAllUsers();

}
