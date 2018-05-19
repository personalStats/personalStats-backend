package com.junior.personalstats.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.junior.personalstats.model.Kill;
import com.junior.personalstats.model.Match;
import com.junior.personalstats.model.Profile;
import com.junior.personalstats.model.DTO.HeaderStatisticsDTO;
import com.junior.personalstats.model.DTO.MatchDTO;
import com.junior.personalstats.model.DTO.MatchDetailsDTO;
import com.junior.personalstats.model.DTO.MatchReferenceDTO;
import com.junior.personalstats.repository.KillRepository;
import com.junior.personalstats.repository.MatchRepository;
import com.junior.personalstats.repository.ProfileRepository;

@RestController
public class MatchController {
	
	@Autowired
	private MatchRepository matchRepository;
	@Autowired
	private KillRepository killRepository;
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private Environment env;

	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@RequestMapping(method=RequestMethod.GET, value="/match/{cdProfile}")
	public List<Match> getDataFromJsonAndPersist(@PathVariable String cdProfile) throws Exception {
		Profile profile = recuperaProfile(cdProfile);
		
		String nuKey = env.getProperty("personalStats.nuKey");
		
		List<MatchDTO> matchList = new ArrayList<MatchDTO>();
		List<Match> matchListConverted = new ArrayList<Match>();
		ObjectMapper mapper = new ObjectMapper();
		
		String urlListaMatch = String.format("https://br1.api.riotgames.com/lol/match/v3/matchlists/by-account/%s?beginIndex=1&season=11&endIndex=5&api_key=%s",profile.getNuAccount(),nuKey);
		MatchDTO matchResponse = new RestTemplate().getForObject(urlListaMatch, MatchDTO.class);
		for (MatchReferenceDTO referenceDTO : matchResponse.getMatchReferences()) {
			MatchDTO matchDTO = new MatchDTO();
			//TODO - PODE VIRAR ASSYNC
			String urlMatch = String.format("https://br1.api.riotgames.com/lol/match/v3/matches/%s?api_key=%s", referenceDTO.getCdMatch(), nuKey);
			matchDTO.setMatchDetailsDTO(new RestTemplate().getForObject(urlMatch, MatchDetailsDTO.class));
			matchDTO.getMatchDetailsDTO().setNuChampion(referenceDTO.getNuChampion());
			matchDTO.getMatchDetailsDTO().setCdMatch(referenceDTO.getCdMatch());
			matchDTO.getMatchDetailsDTO().setDtMatch(referenceDTO.getDtMatch());
			matchList.add(matchDTO);
		
			Match matchFromMatchDTO = new Match().getMatchFromMatchDTO(matchDTO);
			matchListConverted.add(matchFromMatchDTO);
			matchRepository.save(matchFromMatchDTO);

			String urlMatchKills = String.format("https://br1.api.riotgames.com/lol/match/v3/timelines/by-match/%s?api_key=%s", referenceDTO.getCdMatch(), nuKey);
			ObjectNode objectNode = new RestTemplate().getForObject(urlMatchKills, ObjectNode.class);
			JsonNode highDepth = objectNode.get("frames");
			for (JsonNode jsonNode : highDepth) {
				for (JsonNode jsonEvento : jsonNode.get("events")) {
					if(isEventoKill(jsonEvento) && profileIsEnvolved(jsonEvento, mapper, matchFromMatchDTO.getNuParticipant())) {
						saveKill(mapper, jsonEvento, profile, matchFromMatchDTO);
					}
					
				}
			}
		
		}

		return matchListConverted;
	}

	private boolean profileIsEnvolved(JsonNode jsonEvento, ObjectMapper mapper, Integer nuChampion) throws JsonProcessingException {
		Kill kill = mapper.treeToValue(jsonEvento, Kill.class);
		return kill.getNuChampionAssist().contains(nuChampion) || kill.getNuChampionDeath() == nuChampion || kill.getNuChampionKill() == nuChampion;
		
	}

	private Profile recuperaProfile(String cdProfile) throws Exception {
		List<Profile> findAll = profileRepository.findAll();
	
//		if(!profilePromisse.isPresent()) { 
//			throw new Exception("Profile náo encontrado!");
//		}
		
		return findAll.get(0);
	}

	private void saveKill(ObjectMapper mapper, JsonNode jsonEvento, Profile profile, Match match) throws JsonProcessingException {
		Kill kill = mapper.treeToValue(jsonEvento, Kill.class);
		kill.setCdProfile(profile.getCdProfile());
		kill.setCdMatch(match.getCdMatch());
		killRepository.save(kill);
	}

	private boolean isEventoKill(JsonNode jsonEvento) {
		return StringUtils.replace(jsonEvento.get("type").toString(), "\"", "").equals("CHAMPION_KILL");
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, value="/getMatchDetails/{nmProfile}")
	public HeaderStatisticsDTO getMatchDetailsFromProfile(@PathVariable String nmProfile){
		Integer nuSumKills = 0;
		Integer nuSumDeaths = 0;
		Integer nuSumAssists = 0;
		Integer nuGames = 0;
		
		for(Match match : matchRepository.findAll()) {
			nuSumKills += match.getNuKills() == null ? 0 : match.getNuKills();
			nuSumDeaths += match.getNuDeaths() == null ? 0 : match.getNuDeaths();
			nuSumAssists += match.getNuAssists() == null ? 0 : match.getNuAssists();
			nuGames++;
		}
		
		return new HeaderStatisticsDTO(nuSumKills, nuSumDeaths, nuSumAssists, nuGames);
	}
}
