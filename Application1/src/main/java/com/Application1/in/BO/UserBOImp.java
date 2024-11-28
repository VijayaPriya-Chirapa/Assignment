package com.Application1.in.BO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Application1.in.DTO.UserDTO;
import com.Application1.in.Entity.User;
import com.Application1.in.Repository.UserRepository;
import com.Application1.in.mapper.UserMapper;

@Component
public class UserBOImp implements UserBO
{
	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private UserMapper userMapper;

	    @Override
	    public UserDTO getUserById(Long id) {
	        Optional<User> user = userRepository.findById(id);
	        if (user.isPresent()) {
	            return userMapper.userToUserDTO(user.get());
	        } else {
	            return null; // Or handle the case of no user found.
	        }
	    }

	    @Override
	    public UserDTO addUser(User user) {
	        User savedUser = userRepository.save(user);
	        return userMapper.userToUserDTO(savedUser);
	    }

		@Override
		public List<UserDTO> getAllUsers() {
			List<User> users = userRepository.findAll();
		    return userMapper.usersToUserDTOs(users);
		}

	

}
