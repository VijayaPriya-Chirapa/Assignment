package com.Application1.in.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Application1.in.BO.UserBO;
import com.Application1.in.DTO.UserDTO;
import com.Application1.in.Entity.User;
import com.Application1.in.Repository.UserRepository;
import com.Application1.in.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
    private UserBO userBO;

	 @Override
	    public List<UserDTO> getAllUsers() {
	        return userBO.getAllUsers();  // Calls BO to get all users (returns List<UserDTO>)
	    }

	    @Override
	    public UserDTO addUser(User user) {
	        return userBO.addUser(user);  // Calls BO to add a user (works with DTO)
	    }

	    @Override
	    public Optional<UserDTO> getUserById(Long id) {
	        return Optional.ofNullable(userBO.getUserById(id));  // Calls BO to get a user by ID (returns Optional<UserDTO>)
	    }
}
