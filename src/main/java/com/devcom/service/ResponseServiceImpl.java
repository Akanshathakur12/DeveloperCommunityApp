package com.devcom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.dto.ResponseDTO;
import com.devcom.entity.Developer;
import com.devcom.entity.Feed;
import com.devcom.entity.Response;
import com.devcom.exception.DeveloperNotFoundException;
import com.devcom.exception.FeedNotFoundException;
import com.devcom.exception.ResponseNotFoundException;
import com.devcom.repository.DeveloperRepository;
import com.devcom.repository.FeedRepository;
import com.devcom.repository.ResponseRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ResponseServiceImpl implements ResponseService {
	
	private static final Logger log = LoggerFactory.getLogger(ResponseServiceImpl.class);

	@Autowired
	ResponseRepository responseRepository;
	
	@Autowired
	DeveloperRepository developerRepository;
	
	@Autowired
	FeedRepository feedRepository;
	
	@Override
	public Response addResponse(ResponseDTO responseDto) throws DeveloperNotFoundException, FeedNotFoundException {
		Optional<Developer> developer = developerRepository.findById(responseDto.getDevId());
		Optional<Feed> feed = feedRepository.findById(responseDto.getFeedId());
		Response response= new Response();		
		response.setAnswer(responseDto.getAnswer());
		response.setRespDate(responseDto.getRespDate());
		if(developer.isEmpty()) {
			log.error("developer not found");
			throw new DeveloperNotFoundException();
		}
		response.setDeveloper(developer.get());
		if(feed.isEmpty()) {
			log.error("feed not found");
			throw new FeedNotFoundException();
		}
		response.setFeed(feed.get());
		log.info("response saved");
		return responseRepository.save(response);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Response editResponse(int respId, ResponseDTO responseDto) throws ResponseNotFoundException {
		Optional<Response> resp = responseRepository.findById(responseDto.getRespId());
		if(resp.isEmpty()) {
			log.error("response not found");
			throw new ResponseNotFoundException();
		}
		Response getResp=responseRepository.getById(respId);
		getResp.setAnswer(responseDto.getAnswer());
		return responseRepository.save(getResp);
	}

	@Override
	public String removeResponse(int respId) throws ResponseNotFoundException {
		Optional<Response> resp =responseRepository.findById(respId);
		if(!resp.isPresent()) {
			log.error("response not found");
			throw new ResponseNotFoundException();
		}
		responseRepository.deleteById(respId);
		return "Response Deleted";
	
	}
	
	@Override
	public Optional<Response> getResponse(int respid) throws ResponseNotFoundException {
		Optional<Response> opt = responseRepository.findById(respid);
		if (opt.isEmpty()) {
			throw new ResponseNotFoundException();
		}
		return responseRepository.findById(respid);
	}
}