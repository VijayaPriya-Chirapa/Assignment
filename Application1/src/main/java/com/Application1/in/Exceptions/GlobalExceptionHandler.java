package com.Application1.in.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(UserValidationExceptions.class)
    public ResponseEntity<ErrorResponse> handleUserValidationExceptions(UserValidationExceptions ex) {
      ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
	

}
