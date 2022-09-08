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

import com.devcom.entity.Feed;
import com.devcom.repository.FeedRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class FeedServiceImplTest {


	@InjectMocks
	private FeedServiceImpl feedService;
	Feed feed = new Feed(1,"asking question",new Date(),"java",6);

	@Mock
	private FeedRepository feedRepository;

	@Test
	void testAddFeed() {
		
		when(feedRepository.save(feed)).thenReturn(feed);
		assertEquals(feed, feedRepository.save(feed));
       }
	
	
	@Test
	void testGetFeed() {
		
		Optional<Feed> feedOptional = Optional.of(feed);
		when(feedRepository.findById(1)).thenReturn(feedOptional);
		assertThat(feedService.getFeed(1)).isPresent();

	}
	@Test
	void testRemoveFeed() {
		feedRepository.deleteById(1);
		assertThat(feedRepository.existsById(1)).isFalse();
	}
}