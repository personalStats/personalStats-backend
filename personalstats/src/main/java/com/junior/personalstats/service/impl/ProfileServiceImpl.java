package com.junior.personalstats.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Profile getProfileData(String cdProfile) {
		return null;
	}



}
