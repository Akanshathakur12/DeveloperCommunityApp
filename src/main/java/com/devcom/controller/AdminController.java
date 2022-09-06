package com.devcom.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.entity.Developer;
import com.devcom.entity.Feed;
import com.devcom.entity.Response;
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
	
	@PutMapping("/blockdeveloper/{devId}")
	public ResponseEntity<Developer> blockUser(@PathVariable("devId") int devId) {
		Optional<Developer> opt = developerRepository.findById(devId);
		if (opt.isEmpty()) {
			throw new DeveloperNotFoundException();
		}
		Developer savestatus = developerService.blockUser(devId);
		return ResponseEntity.ok().body(savestatus);
		}
	
	@PutMapping("/unblockdeveloper/{devId}")
	public ResponseEntity<Developer> unblockUser(@PathVariable("devId") int devId) {
		Optional<Developer> opt = developerRepository.findById(devId);
		if (opt.isEmpty()) {
			throw new DeveloperNotFoundException();
		}
		Developer savestatus = developerService.unblockUser(devId);
		return ResponseEntity.ok().body(savestatus);
		}
	
	@DeleteMapping("/deletefeed/{feedId}")
	public ResponseEntity<String> removeFeed(@PathVariable("feedId") int feedId) {
		Optional<Feed> opt = feedRepository.findById(feedId);
		if(opt.isEmpty()) {
			throw new FeedNotFoundException();
		}else {
			feedService.removeFeed(feedId);
			return new ResponseEntity<>("Feed Removed", HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/deleteresponse/{respId}")
	public ResponseEntity<String> removeResponse(@PathVariable( "respId") int respId) {
	     
		Optional<Response> opt = responseRepository.findById(respId);
		if(opt.isEmpty()) {
			throw new ResponseNotFoundException();
		}else {
			responseService.removeResponse(respId);
			return new ResponseEntity<>("Response Removed", HttpStatus.OK);
		}	}
}
