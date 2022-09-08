package com.devcom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.dto.DeveloperDTO;
import com.devcom.dto.FeedDTO;
import com.devcom.dto.ResponseDTO;
import com.devcom.entity.Developer;
import com.devcom.entity.Feed;
import com.devcom.entity.Response;
import com.devcom.exception.DeveloperExistsException;
import com.devcom.exception.DeveloperNotFoundException;
import com.devcom.exception.FeedNotFoundException;
import com.devcom.exception.ResponseNotFoundException;
import com.devcom.exception.UserNotFoundException;
import com.devcom.repository.DeveloperRepository;
import com.devcom.repository.FeedRepository;
import com.devcom.repository.ResponseRepository;
import com.devcom.service.DeveloperService;
import com.devcom.service.FeedService;
import com.devcom.service.ResponseService;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
	 
	@Autowired
	DeveloperService developerService;
	
	@Autowired
	DeveloperRepository developerRepository;

	@Autowired
	ResponseService responseService;
	
	@Autowired
	ResponseRepository responseRepository;

	@Autowired
	FeedService feedService;
	
	@Autowired
	FeedRepository feedRepository;
	
	@PostMapping("/AddDetails")
	public ResponseEntity<String> addDeveloper(@RequestBody DeveloperDTO developerDto) throws UserNotFoundException, DeveloperExistsException {
			developerService.addDeveloper(developerDto);
			return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@GetMapping("/GetDetails/{devId}")
	public Optional<Developer> getDeveloper(@PathVariable int devId ) throws DeveloperNotFoundException {
			return developerService.getDeveloper(devId);	
	}
	
	@GetMapping("/AllDetails")
	public List<Developer> getAllDevelopers() {
		return developerService.getAllDevelopers();
	}
	
	@PutMapping("/EditDetails/{devId}")
	public ResponseEntity<Developer> editDeveloper(@PathVariable("devId") int devId, @RequestBody DeveloperDTO developerDto) throws DeveloperNotFoundException {
		Developer updateDev = developerService.editDeveloper(developerDto,devId);
		return ResponseEntity.ok().body(updateDev);
	}
	
	@PostMapping("/AddFeed")
	public ResponseEntity<String> addFeed(@RequestBody FeedDTO feedDto) throws DeveloperNotFoundException
	{
		feedService.addFeed(feedDto);
		return new ResponseEntity<>("Feed added", HttpStatus.OK);
	}
	
	@GetMapping("/GetFeed/{feedId}")
	public Optional<Feed> getFeed(@PathVariable int feedId ) throws FeedNotFoundException {
		return feedService.getFeed(feedId);	
	}
	
	//.................RESPONSE CONTROLLER
	@PostMapping("/AddResponse")
	public ResponseEntity<String> addResponse(@RequestBody ResponseDTO responseDto) throws FeedNotFoundException, DeveloperNotFoundException {
		responseService.addResponse(responseDto);
		return new ResponseEntity<>("Response Added", HttpStatus.OK);
		
	}
	
	@PutMapping("/EditResponse/{respId}")
	public ResponseEntity<Response> editResponse(@PathVariable("respId") int respId,@RequestBody ResponseDTO responseDto) throws ResponseNotFoundException {
		Response updateResp = responseService.editResponse(respId, responseDto);
		return ResponseEntity.ok().body(updateResp);
	}
	

	@GetMapping("/GetResponse/{respId}")
	public Optional<Response> getResponse(@PathVariable int respId ) throws ResponseNotFoundException {
		return responseService.getResponse(respId);	
	}
}