package com.Application1.in;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Application1.in.BO.UserBO;
import com.Application1.in.DTO.UserDTO;
import com.Application1.in.EntityVO.UserVO;
import com.Application1.in.Exceptions.UserNotFoundException;
import com.Application1.in.Exceptions.UserValidationExceptions;
import com.Application1.in.mapper.UserMapper;
import com.Application1.in.service.UserServiceImp;
import static org.junit.jupiter.api.Assertions.*;
class UserServiceTest
{
	@Mock
    private UserBO userBO;
	
	@Mock
	private UserMapper userMapper;
	
	@InjectMocks
	private UserServiceImp userservice;
	
	UserDTO userDTO;
	UserVO userVO;
	
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes mocks
    }
    
    @BeforeEach
    public void setUSers()
    {
    	 userDTO = new UserDTO(1L, "John", "john.doe@example.com", "password123");
         userVO = new UserVO(1L, "John", "john.doe@example.com", "password123");
    }
    
    @Test
    void testAddUserSuccess() {
        
        when(userMapper.userDTOToUser(userDTO)).thenReturn(userVO);
        when(userBO.addUser(userVO)).thenReturn(userVO);
        UserVO result = userservice.addUser(userDTO);

        assertNotNull(result);
        assertEquals(userVO, result);
        assertEquals("John", result.getName());

        // Verify that the methods in userMapper and userBO were called
        verify(userMapper).userDTOToUser(userDTO);
        verify(userBO).addUser(userVO);
    }


        @Test
        void testAddUserThrowsUserValidationExceptions() throws UserValidationExceptions {
            // Arrange
            when(userMapper.userDTOToUser(userDTO)).thenReturn(userVO);
            when(userBO.addUser(userVO)).thenReturn(userVO);

            UserDTO userDTO = null; // or some invalid userDTO that should trigger validation

            // Mock the addUser method of userservice to throw UserValidationExceptions
            when(userservice.addUser(userDTO)).thenThrow(new UserValidationExceptions("Invalid user"));

            // Act & Assert: Call the addUser method and expect UserValidationExceptions to be thrown
            UserValidationExceptions thrown = assertThrows(UserValidationExceptions.class, () -> {
                userservice.addUser(userDTO); // This should throw the UserValidationExceptions
            });

            // Assert that the exception message matches the expected one
            assertEquals("Invalid user", thrown.getMessage());
        }
    
    
    @Test
    void testGetAllUsersSuccess() {
        // Arrange: Create a list of UserVO objects
        UserVO user1 = new UserVO(1L, "John Doe", "john.doe@example.com", "password123");
        UserVO user2 = new UserVO(2L, "Jane Smith", "jane.smith@example.com", "password456");
        List<UserVO> userList = Arrays.asList(user1, user2);

        // Mock the userBO to return a list of users
        when(userBO.getAllUsers()).thenReturn(userList);

        // Act: Call the getAllUsers method
        List<UserVO> result = userservice.getAllUsers();

        // Assert: Verify that the result is the expected list of users
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());

        // Verify that the userBO.getAllUsers method was called
        verify(userBO).getAllUsers();
    }

    @Test
    void testGetAllUsersNoUsersFound() {
        // Arrange: Mock userBO to return an empty list or null
        when(userBO.getAllUsers()).thenReturn(null);

        // Act: Call the getAllUsers method
        List<UserVO> result = userservice.getAllUsers();

        // Assert: Verify that the result is null (if userBO returns null)
        assertNull(result);
        
        // Verify that the userBO.getAllUsers method was called
        verify(userBO).getAllUsers();
    }
    
   
    @Test
    void testGetUserByIdUserFound() {
        // Arrange: Create a UserVO object and wrap it in Optional
        Long userId = 1L;

        Optional<UserVO> userOptional = Optional.of(userVO);

        // Mock userBO to return the Optional<UserVO>
        when(userBO.getUserById(userId)).thenReturn(userOptional);

        // Act: Call the getUserById method
        Optional<UserVO> result = userservice.getUserById(userId);

        // Assert: Verify that the result contains the correct UserVO
        assertTrue(result.isPresent());
        assertEquals(userVO, result.get());
        assertEquals(userId, result.get().getId());
        assertEquals("John", result.get().getName());
        assertEquals("john.doe@example.com", result.get().getEmail());
    }

    @Test
    void testGetUserByIdUserNotFound() {
        // Arrange: Set up the user ID that doesn't exist in the database
        Long userId = 1L;

        // Mock userBO to return an empty Optional (i.e., user not found)
        when(userBO.getUserById(userId)).thenReturn(Optional.empty());

        // Act & Assert: Ensure that a UserNotFoundException is thrown
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userservice.getUserById(userId);
        });

        assertEquals("User with ID " + userId + " not found", exception.getMessage());
    }
}


    