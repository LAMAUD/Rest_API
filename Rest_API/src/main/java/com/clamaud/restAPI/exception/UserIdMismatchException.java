package com.clamaud.restAPI.exception;

public class UserIdMismatchException extends RuntimeException{


	public UserIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
