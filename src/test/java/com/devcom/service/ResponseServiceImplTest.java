package com.devcom.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.devcom.entity.Response;
import com.devcom.exception.ResponseNotFoundException;
import com.devcom.repository.ResponseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class ResponseServiceImplTest {
	
	@InjectMocks 
	private ResponseServiceImpl responseService;
	Response response = new Response("answering to question",new Date(),1);
	
	
	@Mock
	private ResponseRepository responseRepository;
	

	@Test
	void testAddResponse() {
		
	when(responseRepository.save(response)).thenReturn(response);
	assertEquals(response,responseRepository.save(response));	
	}
	

	@Test
	void testAddResponseN() throws ResponseNotFoundException {
		when(responseRepository.save(response)).thenReturn(response);
		try {
	
		assertEquals(response,responseRepository.save(response));
		}
		catch(ResponseNotFoundException e) {
			assertEquals("Feed Not Found",e.getMessage());
		}
		
	}

	@Test
	void testGetResponse() {
		
		Optional<Response> respOptional = Optional.of(response);
		when(responseRepository.findById(1)).thenReturn(respOptional);
		assertThat(responseService.getResponse(1)).isPresent();
		
	}
	
	@Test
	void testRemoveResponse() {
		responseRepository.deleteById(1);
		assertThat(responseRepository.existsById(1)).isFalse();
	}


}
