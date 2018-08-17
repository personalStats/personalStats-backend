package com.junior.personalstats.service;

import java.util.List;
import java.util.Optional;

import com.junior.personalstats.model.Profile;

public interface ProfileService {

	Optional<Profile> getProfileData(String cdProfile);

	List<Profile> findAll();

}
