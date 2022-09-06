package com.devcom.service;

import java.util.List;
import java.util.Optional;

import com.devcom.dto.DeveloperDTO;
import com.devcom.entity.Developer;

public interface DeveloperService {

	public Developer addDeveloper(DeveloperDTO developerdto);
		
	public Optional<Developer> getDeveloper(int devId);
	
	public List<Developer> getAllDevelopers();

	public Developer editDeveloper(DeveloperDTO developerdto, int devId);

	public Developer blockUser(int devId);

	public Developer unblockUser(int devId);

}
