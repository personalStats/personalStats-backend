package com.junior.personalstats.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.junior.personalstats.model.Profile;
import com.junior.personalstats.repository.ProfileRepository;

@RestController
public class ProfileController {

	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private Environment env;


	@RequestMapping(method=RequestMethod.POST, value="/profile/{nmProfile}")
	public ResponseEntity salvarProfile(@PathVariable String nmProfile) {
		String nuRegiao = "br1";
		String nuKey = env.getProperty("personalStats.nuKey");
		String urlProfile = String.format("https://%s.api.riotgames.com/lol/summoner/v3/summoners/by-name/%s?api_key=%s", nuRegiao, nmProfile, nuKey);

		Profile profile = new RestTemplate().getForObject(urlProfile, Profile.class);
		profileRepository.save(profile);

		return ResponseEntity.status(HttpStatus.OK).body(profile);
	}

	@RequestMapping(method=RequestMethod.GET, value="/profile/{cdProfile}")
	public Profile findOneProfile(@PathVariable BigInteger cdProfile) {
		return profileRepository.findById(cdProfile).get();
	}

}
