package com.devcom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundExceptionHandler(UserNotFoundException ex){
		
		return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=DeveloperExistsException.class)
	public ResponseEntity<String> developerExistsExceptionHandler(DeveloperExistsException ex){
		
		return new ResponseEntity<>("Developer already exists",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=DeveloperNotFoundException.class)
	public ResponseEntity<String> developerNotFoundExceptionHandler(DeveloperNotFoundException ex){
		
		return new ResponseEntity<>("Developer Not Found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=FeedNotFoundException.class)
	public ResponseEntity<String> feedNotFoundExceptionHandler(FeedNotFoundException ex){
		
		return new ResponseEntity<>("Feed Not Found",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=InvalidCredentialsException.class)
	public ResponseEntity<String> invalidCredentialsExceptionHandler(InvalidCredentialsException ex){
		
		return new ResponseEntity<>("Invalid Credentials",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=ResponseNotFoundException.class)
	public ResponseEntity<String> responseNotFoundExceptionHandler(ResponseNotFoundException ex){
		
		return new ResponseEntity<>("Response Not Found",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=UserExistsException.class)
	public ResponseEntity<String> userExistsExceptionHandler(UserExistsException ex){
		
		return new ResponseEntity<>("User already exists",HttpStatus.NOT_FOUND);
	}
}
