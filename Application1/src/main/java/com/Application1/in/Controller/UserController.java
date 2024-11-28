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
import com.Application1.in.mapper.UserMapper;
import com.Application1.in.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
    private UserService userService;

	@Autowired
	private UserMapper userMap;
	
	  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	  
	   @GetMapping("/all")
	    public ResponseEntity<List<UserDTO>> getAllUsers() {
	        // Get the list of all users from the service as DTOs
	        List<UserDTO> userDTOs = userService.getAllUsers(); 

	        return new ResponseEntity<>(userDTOs, HttpStatus.OK); // Return 200 OK with the list of UserDTOs
	    }

	 
	   @PostMapping("/add")
	    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody User userDTO) {
	        logger.info("Received request to add new user: {}", userDTO.getName());

	        UserDTO savedUser = userService.addUser(userDTO);

	        logger.info("User added with ID: {}", savedUser.getId());
	        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	    }
    
    
	   @GetMapping("/id/{id}")
	    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
	        logger.info("Received request to fetch user by ID: {}", id);

	        // Fetch the user by ID from the service (it returns Optional<UserDTO>)
	        Optional<UserDTO> userDTOOptional = userService.getUserById(id);

	        if (userDTOOptional.isPresent()) {
	            // User found, return it with HTTP status 200 OK
	            return new ResponseEntity<>(userDTOOptional.get(), HttpStatus.OK);
	        } else {
	            // User not found, return HTTP status 404 NOT FOUND
	            logger.warn("User not found with ID: {}", id);
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
}

