package com.devcom.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.dto.UserDTO;
import com.devcom.entity.User;
import com.devcom.exception.InvalidCredentialsException;
import com.devcom.exception.UserExistsException;
import com.devcom.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(UserDTO userDto) throws UserExistsException {
		Optional<User> opt1 = userRepository.findByUserName(userDto.getUserName());
		if(opt1.isPresent()) {
			log.error("user already exists");
			throw new UserExistsException();
		}
		User user = new User();
				user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		return userRepository.save(user);
	}

	@Override
	public String loginUser(UserDTO userdto) throws InvalidCredentialsException {
		String username = userdto.getUserName();
		String password = userdto.getPassword();
		Optional<User> opt = userRepository.findByUserName(username);

		if (opt.isPresent() && opt.get().getPassword().equals(password)) {
			return "Login Successful";
		} 
		throw new InvalidCredentialsException(); 
		}	
	}