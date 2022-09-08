package com.devcom.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.dto.UserDTO;
import com.devcom.exception.InvalidCredentialsException;
import com.devcom.exception.UserExistsException;
import com.devcom.service.UserService;

@RestController
@Validated
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/Register")
	public ResponseEntity<String> registerUser(@RequestBody @Valid UserDTO userDto) throws UserExistsException {
			userService.registerUser(userDto);
			return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@PostMapping("/Login")
	public ResponseEntity<String> loginUser(@ModelAttribute UserDTO userDto) throws InvalidCredentialsException {
			userService.loginUser(userDto);
			return new ResponseEntity<>("Login Successful", HttpStatus.OK);
			
	}
	

}