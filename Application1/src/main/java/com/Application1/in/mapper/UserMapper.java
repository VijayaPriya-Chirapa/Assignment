package com.Application1.in.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.Application1.in.DTO.UserDTO;
import com.Application1.in.Entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper
{
	 UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

   public UserDTO userToUserDTO(User user);  
   public User userDTOToUser(UserDTO userDTO); 
   public List<UserDTO> usersToUserDTOs(List<User> users);
}
