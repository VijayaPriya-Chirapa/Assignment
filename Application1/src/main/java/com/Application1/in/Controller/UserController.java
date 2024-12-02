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
import com.Application1.in.Entity.UserVO;
import com.Application1.in.Exceptions.UserNotFoundException;
import com.Application1.in.Exceptions.UserValidationExceptions;

import com.Application1.in.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users") // This makes the base URL "/users"
public class UserController {

    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    
    @PostMapping("/add")
    public ResponseEntity<UserVO> addUser(@Valid @RequestBody UserDTO userdto) {
        logger.info("Received request to add a new user with name: {}", userdto.getName());
        UserVO savedUserdto = userService.addUser(userdto);
        return new ResponseEntity<>(savedUserdto, HttpStatus.CREATED);
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
	  public ResponseEntity<UserVO> getUserById(@PathVariable("id") Long id) 
	 {
	        logger.info("Received request to fetch user by ID: {}", id);
	        Optional<UserVO> userDTOOptional = userService.getUserById(id);
	        if (userDTOOptional.isPresent()) 
	        {
	            return new ResponseEntity<>(userDTOOptional.get(), HttpStatus.OK);
	        } else {
	            logger.warn("User not found with ID: {}", id);
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	 }
	 
	 
	 
	 @GetMapping("/health")
     public ResponseEntity<String> healthCheckForGetUserById() {
	 try {
         // Hardcoded input value
         String input = "1"; // Change this to test other cases, e.g., "abc"
         Long userId;  // Validate if the input is a valid Long
         try {
             userId = Long.parseLong(input);
         } catch (NumberFormatException e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body("Health check failed: Input is not a valid numeric ID");
         }
        
         Optional<UserVO> user = userService.getUserById(userId); // Fetch employee by ID
         if (user != null) {
             return ResponseEntity.ok("Success health check");
         } else {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body("Failed health check: User not found");
         }
     } catch (UserNotFoundException e) {
         return ResponseEntity.ok("Success health check");
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body("Health check failed: An unexpected error occurred");
     }
	 
	 }
}

