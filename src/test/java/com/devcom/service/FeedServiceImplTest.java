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

import com.devcom.entity.Feed;
import com.devcom.repository.FeedRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class FeedServiceImplTest {
	
	@Autowired
	FeedRepository feedRepository;

	@Autowired
	private FeedServiceImpl feedService;

	@Mock
	private FeedRepository feeedRepository;

	@Test
	void testAddFeed() {
		Feed feed = new Feed(1,"asking question",new Date(),"java",6);

		when(feeedRepository.save(feed)).thenReturn(feed);
		assertEquals(feed, feeedRepository.save(feed));

	}
	
	@Test
	void testGetFeed() {
		Feed feed = new Feed(3, "answering to question", new Date(), "right", 1);
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