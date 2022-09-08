package com.devcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.entity.Developer;
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
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	FeedService feedService;
	
	@Autowired
	ResponseService responseService;
	
	@Autowired
	DeveloperService developerService;
	
	@Autowired
	DeveloperRepository developerRepository;
	
	@Autowired
	FeedRepository feedRepository;
	
	@Autowired
	ResponseRepository responseRepository;
	
	@PutMapping("/BlockDeveloper/{devId}")
	public ResponseEntity<Developer> blockUser(@PathVariable("devId") int devId) throws DeveloperNotFoundException {
		Developer saveStatus = developerService.blockUser(devId);
		return ResponseEntity.ok().body(saveStatus);
		}
	
	@PutMapping("/UnblockDeveloper/{devId}")
	public ResponseEntity<Developer> unblockUser(@PathVariable("devId") int devId) throws DeveloperNotFoundException {
		Developer saveStatus = developerService.unblockUser(devId);
		return ResponseEntity.ok().body(saveStatus);
		}
	
	@DeleteMapping("/DeleteFeed/{feedId}")
	public ResponseEntity<String> removeFeed(@PathVariable("feedId") int feedId) throws FeedNotFoundException {
			feedService.removeFeed(feedId);
			return new ResponseEntity<>("Feed Removed", HttpStatus.OK);
		}

	
	@DeleteMapping("/DeleteResponse/{respId}")
	public ResponseEntity<String> removeResponse(@PathVariable( "respId") int respId) throws ResponseNotFoundException {
	     	responseService.removeResponse(respId);
			return new ResponseEntity<>("Response Removed", HttpStatus.OK);	
	}
}