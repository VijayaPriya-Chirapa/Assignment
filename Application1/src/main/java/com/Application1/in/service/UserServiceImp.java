package com.Application1.in.service;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Application1.in.DTO.UserDTO;
import com.Application1.in.Entity.UserVO;
import com.Application1.in.Exceptions.UserValidationExceptions;
import com.Application1.in.mapper.UserMapper;
import com.Application1.in.BO.UserBO;

@Service
public class UserServiceImp implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

	@Autowired
    private UserBO userBO;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserVO addUser(UserDTO userdto) {
	    try {
	        UserVO uservo = userMapper.userDTOToUser(userdto);;  // Convert dto to vo
	        UserVO saveduservo = userBO.addUser(uservo); 
	        return saveduservo; 
	    } catch (Exception e) {
	        logger.error("Failed to add user with name: {}", userdto.getName(), e);
	        throw new UserValidationExceptions("Failed to add user");
	    }
	}


	 @Override
	    public List<UserVO> getAllUsers() 
	    {
		 logger.info("Fetching all users from the database.");
		 List<UserVO> uservos=userBO.getAllUsers();
		 if (uservos == null || uservos.isEmpty()) {
	            logger.warn("No users found in the database.");} // Warn if no users are found
	         else {
	            logger.info("Fetched {} users from the database.", uservos.size());}
	        return uservos;
	    }


	@Override
	public Optional<UserVO> getUserById(Long id) {
		
		return userBO.getUserById(id);
	}
	 
	

	 

}
