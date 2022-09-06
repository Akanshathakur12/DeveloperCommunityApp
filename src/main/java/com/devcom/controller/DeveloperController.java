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
	
	@PostMapping("/adddetails")
	public ResponseEntity<String> addDeveloper(@RequestBody DeveloperDTO developerdto) {
		Optional<Developer> opt = developerRepository.findByEmail(developerdto.getEmail());
		if (opt.isPresent()) {
			throw new DeveloperExistsException();
		} else {
			developerService.addDeveloper(developerdto);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		}	
	}
	
	@GetMapping("/getdetails/{devId}")
	public Optional<Developer> getDeveloper(@PathVariable int devId ) {
		Optional<Developer> opt = developerRepository.findById(devId);
		if (opt.isEmpty()) {
			throw new DeveloperNotFoundException();
		} else {
			return developerService.getDeveloper(devId);
		}		
	}
	
	@GetMapping("/alldetails")
	public List<Developer> getAllDevelopers() {
		return developerService.getAllDevelopers();
	}
	
	@PutMapping("/editdetails/{devId}")
	public ResponseEntity<Developer> editDeveloper(@PathVariable("devId") int devId, @RequestBody DeveloperDTO developerdto) {
		Developer updateDev = developerService.editDeveloper(developerdto,devId);
		return ResponseEntity.ok().body(updateDev);
	}
	
	@Autowired
	FeedService feedService;
	
	@Autowired
	FeedRepository feedRepository;
	
	@PostMapping("/addfeed")
	public ResponseEntity<String> addFeed(@RequestBody FeedDTO feeddto)
	{
		feedService.addFeed(feeddto);
		return new ResponseEntity<>("Feed added", HttpStatus.OK);
	}
	
	@GetMapping("/getfeed/{feedid}")
	public Optional<Feed> getFeed(@PathVariable int feedid ) {
		Optional<Feed> opt = feedRepository.findById(feedid);
		if (opt.isEmpty()) {
			throw new FeedNotFoundException();
		} else {
			return feedService.getFeed(feedid);
		}	
	}
	
	@Autowired
	ResponseService responseService;
	
	@Autowired
	ResponseRepository responseRepository;
	
	@PostMapping("/addresponse")
	public ResponseEntity<String> addResponse(@RequestBody ResponseDTO responsedto) {
		Optional<Developer> developer = developerRepository.findById(responsedto.getDevId());
		Optional<Feed> feed = feedRepository.findById(responsedto.getFeedId());	
		if(developer.isEmpty()) {
			throw new DeveloperNotFoundException();
		}
		if(feed.isEmpty()) {
			throw new FeedNotFoundException();
		}
		responseService.addResponse(responsedto);
		return new ResponseEntity<>("Response Added", HttpStatus.OK);
		
	}
	
	@PutMapping("/edit/{respId}")
	public ResponseEntity<Response> editResponse(@PathVariable("respId") int respId,@RequestBody ResponseDTO responsedto) {
		Response updateResp = responseService.editResponse(respId, responsedto);
		return ResponseEntity.ok().body(updateResp);
	}
	

	@GetMapping("/getresponse/{respid}")
	public Optional<Response> getResponse(@PathVariable int respid ) {
		Optional<Response> opt = responseRepository.findById(respid);
		if (opt.isEmpty()) {
			throw new ResponseNotFoundException();
		} else {
			return responseService.getResponse(respid);
		}		
	}
}