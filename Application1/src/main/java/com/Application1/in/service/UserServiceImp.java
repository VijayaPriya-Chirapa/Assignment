package com.Application1.in.service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Application1.in.DTO.UserDTO;
import com.Application1.in.EntityVO.UserVO;
import com.Application1.in.Exceptions.UserNotFoundException;
import com.Application1.in.Exceptions.UserValidationExceptions;
import com.Application1.in.mapper.UserMapper;
import com.Application1.in.util.Constants;
import com.Application1.in.BO.UserBO;

@Service
public class UserServiceImp implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(Constants.LOGGER_NAME_SERVICE);

	@Autowired
    private UserBO userBO;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserVO addUser(UserDTO userdto) 
	{
		  logger.info("Adding new user");   
		    UserVO uservo = userMapper.userDTOToUser(userdto);;  // Convert dto to vo
		    UserVO saveduservo = userBO.addUser(uservo);
		    return saveduservo;
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
	     if (id == null) {
	         throw new IllegalArgumentException("User ID cannot be null");
	     }
	     logger.info("Fetching user with ID: {}", id);
	     Optional<UserVO> user = userBO.getUserById(id);
	     
	     if (user.isPresent()) {
	         logger.info("User found with ID: {}", id);
	         return user;
	     } else {
	         logger.warn("User not found with ID: {}", id);
	         throw new UserNotFoundException("User with ID " + id + " not found");
	     }
	 }
	 
	 
	
}
