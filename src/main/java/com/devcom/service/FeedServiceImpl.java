package com.devcom.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.dto.FeedDTO;
import com.devcom.entity.Developer;
import com.devcom.entity.Feed;
import com.devcom.exception.DeveloperNotFoundException;
import com.devcom.exception.FeedNotFoundException;
import com.devcom.exception.QueryExistsException;
import com.devcom.repository.DeveloperRepository;
import com.devcom.repository.FeedRepository;

@Service
public class FeedServiceImpl implements FeedService {
	
	private static final Logger log = LoggerFactory.getLogger(FeedServiceImpl.class);

	@Autowired
	FeedRepository feedRepository;
	
	@Autowired
	DeveloperRepository developerRepository;	
	
	@Override
	public Feed addFeed(FeedDTO feeddto) throws DeveloperNotFoundException {
		Optional<Developer> developer = developerRepository.findById(feeddto.getDevId());
		if(developer.isEmpty()) {
			log.error("developer not found");
			throw new DeveloperNotFoundException();
		}
		Optional<Feed> feed = feedRepository.findByQuery(feeddto.getQuery());
		if(feed.isPresent()) {
			log.error("query already exists");
			throw new QueryExistsException();
		}
		Feed feed1=new Feed();
		feed1.setQuery(feeddto.getQuery());
		feed1.setTopic(feeddto.getTopic());
		feed1.setFeedDate(feeddto.getFeedDate());
		feed1.setDeveloper(developer.get());
		log.info("feed saved");
		return  feedRepository.save(feed1);
	}
	
	@Override
	public String removeFeed(int feedid) throws FeedNotFoundException {
		
		Optional<Feed> feed = feedRepository.findById(feedid);
		if(!feed.isPresent()) {
			log.error("feed not found");
			throw new FeedNotFoundException();
		}
		feedRepository.deleteById(feedid);
        return "Feed Deleted";
	
	}
	@Override
	public Optional<Feed> getFeed(int feedid) throws FeedNotFoundException {
		Optional<Feed> opt = feedRepository.findById(feedid);
		if (opt.isEmpty()) {
			throw new FeedNotFoundException();
		}
		return feedRepository.findById(feedid);
	}

}