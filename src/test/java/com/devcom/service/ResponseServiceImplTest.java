package com.devcom.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.devcom.entity.Response;
import com.devcom.repository.ResponseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class ResponseServiceImplTest {
	
	@Autowired 
	private ResponseServiceImpl responseService;
	
	@Mock
	private ResponseRepository responseRepository;
	

	@Test
	void testAddResponse() {
		Response response = new Response("answering to question",new Date(),1);
		
	when(responseRepository.save(response)).thenReturn(response);
	assertEquals(response,responseRepository.save(response));	
	}
	



//	@BeforeAll
//     public static void setUP() {
//		Response res= new Response("ans",new Date(),1);
//	}

//	@Test
//	void testEditResponse() {
//		
//		Response res=responseRepository.getById(1).;
//		res.setAnswer("changed");
//		responseRepository.save(res);
//		assertNotEquals("5000",responseRepository.findById(1).get().getAnswer());
//		
//	}

	@Test
	void testGetResponse() {
		Response res= new Response("answering to question",new Date(),1);
		Optional<Response> respOptional = Optional.of(res);
		when(responseRepository.findById(1)).thenReturn(respOptional);
		assertThat(responseService.getResponse(1)).isPresent();
		
	}
	
	@Test
	void testRemoveResponse() {
		responseRepository.deleteById(1);
		assertThat(responseRepository.existsById(1)).isFalse();
	}


}
