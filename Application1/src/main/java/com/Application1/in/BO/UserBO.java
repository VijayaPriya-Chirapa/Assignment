package com.Application1.in.BO;

import java.util.List;
import java.util.Optional;

import com.Application1.in.DTO.UserDTO;
import com.Application1.in.EntityVO.UserVO;

public interface UserBO
{
	
	public UserVO addUser(UserVO uservo);
	 public List<UserVO> getAllUsers();
	 public Optional<UserVO> getUserById(Long id);

}
