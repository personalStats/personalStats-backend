package com.junior.personalstats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.junior.personalstats.model.ProfileDTO;
import com.junior.personalstats.repository.ProfileRepository;

@RestController
public class ProfileController {

	@Autowired
	private ProfileRepository profileRepository;
	
	@RequestMapping(method=RequestMethod.GET, value="/profile/{cdProfile}")
	public ProfileDTO findOneProfile(@PathVariable Integer cdProfile) {
		return new ProfileDTO(1, "TESTE","122");
		//return profileRepository.findById(cdProfile);
	}
}
