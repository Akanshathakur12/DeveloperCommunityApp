package com.devcom.controller;


import java.util.Optional;

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
import com.devcom.entity.User;
import com.devcom.exception.UserExistsException;
import com.devcom.repository.UserRepository;
import com.devcom.service.UserService;

@RestController
@Validated
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody @Valid UserDTO userdto) {
		Optional<User> opt1 = userRepository.findByUserName(userdto.getUserName());
		if (opt1.isPresent()) {
			throw new UserExistsException();
		} else {
			userService.registerUser(userdto);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		}			
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@ModelAttribute UserDTO userdto) {
		String username = userdto.getUserName();
		String password = userdto.getPassword();
		Optional<User> opt = userRepository.findByUserName(username);

		if (opt.isPresent() && opt.get().getPassword().equals(password)) {
			return new ResponseEntity<>("Login successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Incorrect credentials", HttpStatus.OK);
		}	
	}
	

}