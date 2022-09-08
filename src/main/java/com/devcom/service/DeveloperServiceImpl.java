package com.devcom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devcom.dto.DeveloperDTO;
import com.devcom.entity.Developer;
import com.devcom.entity.User;
import com.devcom.exception.DeveloperExistsException;
import com.devcom.exception.DeveloperNotFoundException;
import com.devcom.exception.UserNotFoundException;
import com.devcom.repository.DeveloperRepository;
import com.devcom.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service 
public class DeveloperServiceImpl implements DeveloperService {
	
	private static final Logger log = LoggerFactory.getLogger(DeveloperServiceImpl.class);
	
	@Autowired
	DeveloperRepository developerRepository; 
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Developer addDeveloper(DeveloperDTO developerdto) throws UserNotFoundException,DeveloperExistsException {
		Optional<User> user = userRepository.findById(developerdto.getUserId());
		if(user.isEmpty()) {
			log.error("user is not found");
			throw new UserNotFoundException();
		}
		Optional<Developer> opt = developerRepository.findByEmail(developerdto.getEmail());
		if(opt.isPresent()) {
			throw new DeveloperExistsException();
		}
		Developer developer = new Developer();
		developer.setName(developerdto.getName());
		developer.setEmail(developerdto.getEmail());
		developer.setSkillLevel(developerdto.getSkillLevel());
		developer.setUser(user.get());
		log.info("user is saved");
		return developerRepository.save(developer);
	}

	@Override
	public Optional<Developer> getDeveloper(int devId) throws DeveloperNotFoundException {
		Optional<Developer> opt = developerRepository.findById(devId);
		if (opt.isEmpty()) {
			throw new DeveloperNotFoundException();
		}
		return developerRepository.findById(devId);
	}

	@Override
	public List<Developer> getAllDevelopers() {
		return developerRepository.findAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Developer editDeveloper(DeveloperDTO developerdto, int devId) throws DeveloperNotFoundException {
		Optional<Developer> opt = developerRepository.findById(devId);
		if (opt.isEmpty()) {
			throw new DeveloperNotFoundException();
		}
		Developer getDev = developerRepository.getById(devId);
		getDev.setSkillLevel(developerdto.getSkillLevel());
		getDev.setName(developerdto.getName());
		return developerRepository.save(getDev);
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Developer blockUser(int devId) throws DeveloperNotFoundException {
		Optional<Developer> opt = developerRepository.findById(devId);
		if (opt.isEmpty()) {
			throw new DeveloperNotFoundException();
		}
		Developer getDev = developerRepository.getById(devId);
		getDev.setBlocked(true);
		return developerRepository.save(getDev);
		}

	@SuppressWarnings("deprecation")
	@Override
	public Developer unblockUser(int devId) throws DeveloperNotFoundException {
		Optional<Developer> opt = developerRepository.findById(devId);
		if (opt.isEmpty()) {
			throw new DeveloperNotFoundException();
		}
		Developer getDev = developerRepository.getById(devId);
		getDev.setBlocked(false);
		return developerRepository.save(getDev);
		
	}
}