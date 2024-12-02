package com.Application1.in.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.Application1.in.Controller.UserController;

@ControllerAdvice
public class GlobalExceptionHandler 
{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	  @ExceptionHandler(UserValidationExceptions.class)
	    public ResponseEntity<String> handleUserValidationException(UserValidationExceptions ex) {
	        // Return a custom error message with 400 BAD_REQUEST status
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
	    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
	        String errorMessage = "Invalid ID format. Expected a valid number.";
	        logger.warn(errorMessage + " Error: " + ex.getMessage());
	        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	    }
	
	  @ExceptionHandler(UserNotFoundException.class)
	    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
	        // Return a custom error message with 400 BAD_REQUEST status
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	  
}
