package com.devcom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.dto.UserDTO;
import com.devcom.entity.User;
import com.devcom.exception.UserExistsException;
import com.devcom.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(UserDTO userdto) {
		User user = new User();
		
		user.setUserName(userdto.getUserName());
		user.setPassword(userdto.getPassword());
		user.setRole(userdto.getRole());
		Optional<User> opt1 = userRepository.findByUserName(userdto.getUserName());
		if(opt1.isPresent()) {
			throw new UserExistsException();
		}else {
			return userRepository.save(user);
		}
	}
	

}