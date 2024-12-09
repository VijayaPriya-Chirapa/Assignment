package com.Application1.in.Exceptions;

public class UserValidationExceptions extends RuntimeException {

    // Constructor to pass custom message when the exception is thrown
    public UserValidationExceptions(String message) {
        super(message);
    }
}
