package com.Application1.in;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.Application1.in.BO.UserBOImp;
import com.Application1.in.EntityVO.UserVO;
import com.Application1.in.Exceptions.UserValidationExceptions;
import com.Application1.in.Repository.UserRepository;

public class BOTests 
{
	@Mock
	private UserRepository userrepo;
	
	@InjectMocks
	private UserBOImp userbo;
	
	UserVO uservo;
	UserVO uservo1;
	
	@BeforeEach
	public void setup()
	{
		MockitoAnnotations.openMocks(this);
	}
	@BeforeEach
	public void setUserVO(){
        uservo = new UserVO(1L, "John", "john.doe@example.com", "password123");
        uservo1=new UserVO(2L, "ram", "ram.sun@example.com", "password773");
	}
	
	@Test
	public void adduserBOtest()
	{
		when(userrepo.save(uservo)).thenReturn(uservo);
		assertEquals(uservo,userbo.addUser(uservo));
		verify(userrepo).save(uservo);
	}
	
	 @Test
	    void testAddUserThrowsUserValidationExceptions() {
	        // Arrange: Mock save method to throw a UserValidationExceptions
	        UserValidationExceptions exception = new UserValidationExceptions("Invalid user data");
	        when(userrepo.save(uservo)).thenThrow(exception);

	        // Act & Assert: Verify that UserValidationExceptions is thrown
	        UserValidationExceptions thrown = assertThrows(UserValidationExceptions.class, () -> {
	            userbo.addUser(uservo);
	        });

	        // Assert: Check the exception message
	        assertEquals("Invalid user data", thrown.getMessage());
	        verify(userrepo).save(uservo);  // Ensure save() was called
	    }
	 
	 @Test
	    void testGetAllUsers() {
		 List<UserVO> users = Arrays.asList(uservo, uservo1);
	       when(userrepo.findAll()).thenReturn(users);
	       assertEquals(users,userbo.getAllUsers());
	       
	      
	 }
	 
	 @Test
	    public void testGetUserByIdFound() {
	        // Mock the repository to return an Optional with the user when findById is called with 1L
	        when(userrepo.findById(1L)).thenReturn(Optional.of(uservo));

	        Optional<UserVO> result = userbo.getUserById(1L);

	        verify(userrepo, times(1)).findById(1L);

	        assertTrue(result.isPresent());
	        assertEquals(uservo, result.get());
	    }
	 
	 @Test
	    void testGetUserByIdNotFound() {
	        // Arrange: Mock findById() to return an empty Optional
	        when(userrepo.findById(1L)).thenReturn(Optional.empty());

	        // Act: Call getUserById() method
	        Optional<UserVO> result = userbo.getUserById(1L);

	        // Assert: Verify the result is empty
	        assertFalse(result.isPresent());
	        verify(userrepo).findById(1L);  // Ensure findById() was called
	    }
	 
	
	
	

}
