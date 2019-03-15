package com.clamaud.restAPI.exception;

public class UserNotFoundException extends RuntimeException{

	
	public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
