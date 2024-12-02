package com.Application1.in.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.Application1.in.Controller.UserController;

@ControllerAdvice
public class GlobalExceptionHandler 
{
 

	  @ExceptionHandler(UserValidationExceptions.class)
	    public ResponseEntity<String> handleUserValidationException(UserValidationExceptions ex) {
	        // Return a custom error message with 400 BAD_REQUEST status
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }

	
	  @ExceptionHandler(UserNotFoundException.class)
	    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
	        // Return a custom error message with 400 BAD_REQUEST status
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	  
}
