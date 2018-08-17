package com.junior.personalstats.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.junior.personalstats.model.Profile;
import com.junior.personalstats.repository.ProfileRepository;
import com.junior.personalstats.service.ProfileService;
import com.junior.personalstats.util.MongoHandler;

@Service
public class ProfileServiceImpl extends MongoHandler implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public Optional<Profile> getProfileData(String cdProfile) {

		Profile profile = new Profile();
		profile.setCdProfile(cdProfile);

		return profileRepository.findOne(Example.of(profile));
	}

	@Override
	public List<Profile> findAll(){
		return profileRepository.findAll();
	}

}
