package com.Application1.in.Controller;

import java.util.ArrayList;
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
import com.Application1.in.EntityVO.UserVO;


import com.Application1.in.service.UserService;
import com.Application1.in.util.Constants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users") // This makes the base URL "/users"
public class UserController {

    @Autowired
    private UserService userService;
    
    private static final Logger logger = LoggerFactory.getLogger(Constants.LOGGER_NAME_CONTROLLER);

    
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userdto) {
    	logger.info("Received request to add a new user with name: {}", userdto.getName());

        try {
            UserVO savedUserdto = userService.addUser(userdto);
            logger.info("User created successfully with ID: {}", savedUserdto.getId());
            return new ResponseEntity<>(savedUserdto, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
        }
        }
    
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers()
    {
    	logger.info("Received request to get all users.");//log
        List<UserVO> userDTOs = userService.getAllUsers();
        if(userDTOs == null) {userDTOs = new ArrayList<>();}
        if (userDTOs.isEmpty()) 
        {
            return new ResponseEntity<>("No users found", HttpStatus.NOT_FOUND);
        }
        logger.info("Retrieved {} users.", userDTOs.size());//log
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }
    


	 
	 @GetMapping("/id/{id}")
	 public ResponseEntity<UserVO> getUserById(@PathVariable("id") Long id) {
		 logger.info("Received request to fetch user by ID: {}", id);
	     Optional<UserVO> userDTOOptional = userService.getUserById(id);
	     if (userDTOOptional.isPresent()) {
	         return new ResponseEntity<>(userDTOOptional.get(), HttpStatus.OK);
	     } else {
	            logger.warn("User not found with ID: {}", id);
	         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	     }
	 }

	 
	 
	 @GetMapping("/health/{value}")
	 public ResponseEntity<String> healthCheckForGetUserById(@PathVariable("value") String value) {
	     try {
	         Long userId;
	         try {
	             userId = Long.parseLong(value);
	         } catch (NumberFormatException e) {
	             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                     .body("Health check failed: Input is not a valid numeric ID");
	         }

	         // Attempt to fetch user by ID
	         Optional<UserVO> user = userService.getUserById(userId);

	         // If user exists, return success, otherwise return error
	         if (user.isPresent()) {
	             return ResponseEntity.ok("Success health check");
	         } else {
	             return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                     .body("Failed health check: User not found");
	         }
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                 .body("Health check failed: An unexpected error occurred");
	     }
	 }
}
