package com.Application1.in.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Application1.in.DTO.UserDTO;
import com.Application1.in.Entity.User;
import com.Application1.in.Exceptions.UserValidationExceptions;
import com.Application1.in.mapper.UserMapper;
import com.Application1.in.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
    private UserService userService;

	@Autowired
	private UserMapper userMapper;
	
	  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	  
	   @GetMapping("/all")
	    public ResponseEntity<List<UserDTO>> getAllUsers() 
	   {
	     List<UserDTO> userDTOs = userService.getAllUsers(); 
	     return new ResponseEntity<>(userDTOs, HttpStatus.OK); // Return 200 OK with the list of UserDTOs
	    }

	 
	   @PostMapping("/add")
	    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody User userDTO) {
	        logger.info("Received request to add new user: {}", userDTO.getName());
	        if(userDTO.getName()==null||userDTO.getName().length()<2||userDTO.getName().length()>50)
	        {
	        	logger.error("Validation failed:Name should be not null and within 2 and 50 characters");
	         throw new UserValidationExceptions("Name should be not null and within 2 and 50 characters");
	         }
	       
	        UserDTO savedUser = userService.addUser(userDTO);
	        logger.info("User added with ID: {}", savedUser.getId());
	        
	        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	    }
    
    
}

