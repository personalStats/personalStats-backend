package com.junior.personalstats.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.junior.personalstats.model.Match;
import com.junior.personalstats.model.MatchDTO;
import com.junior.personalstats.model.MatchDetailsDTO;
import com.junior.personalstats.model.MatchReferenceDTO;
import com.junior.personalstats.repository.MatchRepository;

@RestController
public class MatchController {
	
	@Autowired
	private MatchRepository matchRepository;

	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@RequestMapping(method=RequestMethod.GET, value="/match/{cdMatch}")
	public List<Match> getDataFromJsonAndPersist(@PathVariable Integer cdMatch) throws Exception {
		String nuKey = "RGAPI-a457349c-ee87-4fba-82c4-fb6da47e7a4c";
		
		List<MatchDTO> matchList = new ArrayList<MatchDTO>();
		List<Match> matchListConverted = new ArrayList<Match>();
		
		MatchDTO matchResponse = new RestTemplate().getForObject("https://br1.api.riotgames.com/lol/match/v3/matchlists/by-account/1123420?beginIndex=1&season=11&endIndex=5&api_key=" + nuKey, MatchDTO.class);
		for (MatchReferenceDTO referenceDTO : matchResponse.getMatchReferences()) {
			MatchDTO matchDTO = new MatchDTO();
			//TODO - PODE VIRAR ASSYNC
			String urlMatch = String.format("https://br1.api.riotgames.com/lol/match/v3/matches/%s?api_key=%s", referenceDTO.getCdMatch(), nuKey);
			matchDTO.setMatchDetailsDTO(new RestTemplate().getForObject(urlMatch, MatchDetailsDTO.class));
			matchDTO.getMatchDetailsDTO().setNuChampion(referenceDTO.getNuChampion());
			matchDTO.getMatchDetailsDTO().setCdMatch(referenceDTO.getCdMatch());
			matchDTO.getMatchDetailsDTO().setDtMatch(referenceDTO.getDtMatch());
			matchList.add(matchDTO);
			matchListConverted.add(new Match().getMatchFromMatchDTO(matchDTO));
			
		//	matchRepository.save(new Match().getMatchFromMatchDTO(matchDTO));
			
		}

		return matchListConverted;
		
	}
	
}
