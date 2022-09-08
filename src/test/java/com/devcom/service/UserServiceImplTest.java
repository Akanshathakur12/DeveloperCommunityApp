package com.devcom.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.devcom.entity.User;
import com.devcom.exception.UserExistsException;
import com.devcom.repository.UserRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
	
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	UserServiceImpl userService= new UserServiceImpl();
	User user = new User("abc","abc","asd");
	
	@Test
	void testRegisterUser() {
      		
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user,userRepository.save(user));
	}

	@Test
	void testRegisterUserN() {
		
		when(userRepository.save(user)).thenReturn(user);
		try {
			assertEquals(user,userRepository.save(user));
		}
		catch(UserExistsException e){
			assertEquals("User already Exist",e.getMessage());
			
		}
		
	}
	
}

