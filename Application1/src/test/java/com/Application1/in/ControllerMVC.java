package com.Application1.in;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import com.Application1.in.Controller.UserController;
import com.Application1.in.DTO.UserDTO;
import com.Application1.in.EntityVO.UserVO;
import com.Application1.in.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; // WebMvcTest to focus only on the controller
import org.springframework.boot.test.mock.mockito.MockBean; // Mock the service layer
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

@WebMvcTest(UserController.class) // Focus on testing the UserController only
public class ControllerMVC {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;  // Mocked service layer

    private UserVO userVO;

    @BeforeEach
    public void setup() {
        userVO = new UserVO(1L, "John", "john.doe@example.com", "password123");
    }

    @Test
    public void testAddUser() throws Exception {
        when(userService.addUser(any(UserDTO.class))).thenReturn(userVO);

        mockMvc.perform(post("/api/users/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\", \"email\":\"john.doe@example.com\", \"password\":\"password123\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.password").value("password123"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(userVO));

        mockMvc.perform(get("/api/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"));
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(userVO));

        mockMvc.perform(get("/api/users/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testGetUserByIdNotFound() throws Exception {
        when(userService.getUserById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/id/999"))
                .andExpect(status().isNotFound());
    }



    @Test
    public void testHealthCheckInvalidInput() throws Exception {
        String invalidInput = "abc";  // Non-numeric input
        

        mockMvc.perform(get("/api/users/health/{value}", invalidInput))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Health check failed: Input is not a valid numeric ID"));
    }
    
    @Test
    public void testHealthCheckUserFound() throws Exception {
        // Mock the service to return a user with ID 1
    	when(userService.getUserById(1L)).thenReturn(Optional.of(userVO));
    	String invalidInput = "1";  
        // Perform the GET request to the health endpoint
        mockMvc.perform(get("/api/users/health/{value}",invalidInput))
                .andDo(print())  // Print response for debugging
                .andExpect(status().isOk())  // Expect 200 OK for valid user
                .andExpect(content().string("Success health check"));  // Expected success message
    }

    

   
    
}

