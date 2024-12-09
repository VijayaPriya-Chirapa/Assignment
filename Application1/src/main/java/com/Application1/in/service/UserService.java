package com.Application1.in.service;

import java.util.List;
import java.util.Optional;

import com.Application1.in.DTO.UserDTO;
import com.Application1.in.EntityVO.UserVO;

public interface UserService 
{
	public List<UserVO> getAllUsers();
	public UserVO addUser(UserDTO userdto);
	public Optional<UserVO> getUserById(Long id);

}

