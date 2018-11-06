package com.junior.personalstats.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.junior.personalstats.constants.TypeEnum;
import com.junior.personalstats.exceptions.ProfileNotFoundException;
import com.junior.personalstats.model.Dragon;
import com.junior.personalstats.model.Kill;
import com.junior.personalstats.model.Match;
import com.junior.personalstats.model.Profile;
import com.junior.personalstats.model.dto.ChampionStatisticsDTO;
import com.junior.personalstats.model.dto.HeaderStatisticsDTO;
import com.junior.personalstats.model.dto.MatchDTO;
import com.junior.personalstats.model.dto.MatchDetailsDTO;
import com.junior.personalstats.model.dto.MatchReferenceDTO;
import com.junior.personalstats.model.dto.ParticipantDTO;
import com.junior.personalstats.service.ChampionService;
import com.junior.personalstats.service.DragonService;
import com.junior.personalstats.service.KillService;
import com.junior.personalstats.service.MatchService;
import com.junior.personalstats.service.ProfileService;
import com.junior.personalstats.service.impl.KillServiceImpl;

@RestController
@SuppressWarnings("rawtypes")
public class MatchController {

	@Autowired
	private MatchService matchService;
	@Autowired
	private KillService killService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private DragonService dragonService;
	@Autowired
	private ChampionService championService;
	@Autowired
	private Environment env;

	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@RequestMapping(method=RequestMethod.GET, value="/match/{cdProfile}")
	public List<Match> getDataFromJsonAndPersist(@PathVariable String cdProfile) throws Exception {
		Profile profile = recuperaProfile(cdProfile);

		String nuKey = env.getProperty("personalStats.nuKey");

		List<MatchDTO> matchList = new ArrayList<>();
		List<Match> matchListConverted = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();

		String urlListaMatch = String.format("https://br1.api.riotgames.com/lol/match/v3/matchlists/by-account/%s?beginIndex=1&queue=420&season=11&api_key=%s",profile.getNuAccount(),nuKey);
		MatchDTO matchResponse = new RestTemplate().getForObject(urlListaMatch, MatchDTO.class);
		for (MatchReferenceDTO referenceDTO : matchResponse.getMatchReferences()) {
			MatchDTO matchDTO = new MatchDTO();
			//TODO - PODE VIRAR ASSYNC E IR PARA UMA CAMADA DE SERVIÇO
			String urlMatch = String.format("https://br1.api.riotgames.com/lol/match/v3/matches/%s?api_key=%s", referenceDTO.getCdMatch(), nuKey);
			matchDTO.setMatchDetailsDTO(new RestTemplate().getForObject(urlMatch, MatchDetailsDTO.class));
			matchDTO.getMatchDetailsDTO().setNuChampion(referenceDTO.getNuChampion());
			matchDTO.getMatchDetailsDTO().setCdMatch(referenceDTO.getCdMatch());
			matchDTO.getMatchDetailsDTO().setDtMatch(referenceDTO.getDtMatch());
			matchList.add(matchDTO);

			Match matchFromMatchDTO = new Match().getMatchFromMatchDTO(matchDTO);
			matchFromMatchDTO.setProfile(profile);
			matchListConverted.add(matchFromMatchDTO);
			matchService.save(matchFromMatchDTO);

			String urlMatchKills = String.format("https://br1.api.riotgames.com/lol/match/v3/timelines/by-match/%s?api_key=%s", referenceDTO.getCdMatch(), nuKey);
			ObjectNode objectNode = new RestTemplate().getForObject(urlMatchKills, ObjectNode.class);
			JsonNode highDepth = objectNode.get("frames");
			for (JsonNode jsonNode : highDepth) {
				for (JsonNode jsonEvento : jsonNode.get("events")) {
					if(isEventoKill(jsonEvento) &&  profileIsEnvolved(jsonEvento, mapper, matchFromMatchDTO.getNuParticipant())) {
						saveKill(mapper, jsonEvento, profile, matchFromMatchDTO, matchDTO.getMatchDetailsDTO().getParticipants());
					}
					if(isEventoDragon(jsonEvento) && profileIsEnvolved(jsonEvento, matchFromMatchDTO, matchDTO.getMatchDetailsDTO().getParticipants())) {
						saveDragon(jsonEvento, profile, matchFromMatchDTO);
					}

				}
			}

		}

		return matchListConverted;
	}

	private boolean profileIsEnvolved(JsonNode jsonEvento, Match matchFromMatchDTO,	List<ParticipantDTO> participants) {

		int nuKiller = jsonEvento.get("killerId").asInt();
		return participants.stream().filter(p -> p.getNuTeam() == matchFromMatchDTO.getNuTeam()).anyMatch(p -> p.getNuParticipant() == nuKiller);

	}

	private boolean profileIsEnvolved(JsonNode jsonEvento, ObjectMapper mapper, Integer nuChampion) throws JsonProcessingException {
		Kill kill = mapper.treeToValue(jsonEvento, Kill.class);
		return kill.getNuChampionAssist().contains(nuChampion) || kill.getNuChampionDeath() == nuChampion || kill.getNuChampionKill() == nuChampion;
	}


	private Profile recuperaProfile(String cdProfile) throws ProfileNotFoundException{
		Optional<Profile> profile = profileService.getProfileData(cdProfile);
		if(!profile.isPresent()) {
			throw new ProfileNotFoundException();
		}

		return profile.get();
	}

	private void saveKill(ObjectMapper mapper, JsonNode jsonEvento, Profile profile, Match match, List<ParticipantDTO> listParticipants) throws JsonProcessingException {
		Kill kill = mapper.treeToValue(jsonEvento, Kill.class);
		kill.setCdProfile(profile.getCdProfile());
		kill.setNuGameId(match.getNuGameId());
		kill.setNuChampionKill(getChampionFromParticipant(listParticipants, kill.getNuChampionKill()));
		kill.setNuChampionDeath(getChampionFromParticipant(listParticipants, kill.getNuChampionDeath()));
		kill.setNuChampionAssist(getChampionFromParticipant(listParticipants, kill.getNuChampionAssist()));
		kill.setNuParticipant(match.getNuParticipant());
		kill.setType(getTypeKill(match, kill));
		killService.save(kill);
	}

	private void saveDragon(JsonNode jsonEvento, Profile profile, Match match) {
		Dragon dragon = new Dragon();
		dragon.setCdProfile(profile.getCdProfile());
		dragon.setNuGameId(match.getNuGameId());
		dragon.setDeTypeDragon(jsonEvento.get("monsterSubType").toString());
		dragon.setNuTimeDragon(jsonEvento.get("timestamp").toString());

		dragonService.save(dragon);
	}

	private String getTypeKill(Match match, Kill kill) {
		if(kill.getNuChampionKill() == match.getNuChampion()) {
			return TypeEnum.KILL.getType();
		}else if(kill.getNuChampionDeath() == match.getNuChampion()) {
			return TypeEnum.DEATH.getType();
		}else if(kill.getNuChampionAssist().contains(match.getNuChampion())) {
			return TypeEnum.ASSIST.getType();
		}

		return null;
	}

	private Integer getChampionFromParticipant(List<ParticipantDTO> listParticipants, Integer nuChampion) {
		Optional<ParticipantDTO> listaFiltrada = listParticipants.stream()
			.filter(participantDTO -> nuChampion == participantDTO.getNuParticipant())
			.filter(participantDTO -> nuChampion == participantDTO.getNuParticipant())
			.findFirst();

		return listaFiltrada.isPresent() ? listaFiltrada.get().getNuChampion() : null;
	}

	private List<Integer> getChampionFromParticipant(List<ParticipantDTO> listParticipants, List<Integer> listAssists) {
		return listParticipants.stream()
								.filter(participantDTO -> listAssists.contains(participantDTO.getNuParticipant()))
								.mapToInt(ParticipantDTO::getNuChampion)
								.mapToObj(Integer::valueOf)
						        .collect(Collectors.toList());
	}

	private boolean isEventoKill(JsonNode jsonEvento) {
		return jsonEvento.get("type") != null && StringUtils.replace(jsonEvento.get("type").toString(), "\"", "").equals("CHAMPION_KILL");
	}

	private boolean isEventoDragon(JsonNode jsonEvento) {
		return jsonEvento.get("monsterType") != null && StringUtils.replace(jsonEvento.get("monsterType").toString(), "\"", "").equals("DRAGON");
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, value="/getHeaderStatistics/{cdProfile}")
	public ResponseEntity getHeaderStatisticsFromProfile(@PathVariable String cdProfile){

		HeaderStatisticsDTO headerStatistics = matchService.findHeaderStatisticsByProfile(cdProfile);
		if(headerStatistics == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(headerStatistics, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, value="/getKillingspreePerChampionByProfile/{cdProfile}")
	public ResponseEntity getDoubleKillsChampionByProfile(@PathVariable String cdProfile) {
		List<HeaderStatisticsDTO> listaKillingspree = matchService.findKillingspreePerChampionByProfile(cdProfile);
		if(listaKillingspree.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(listaKillingspree, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, value="/getChampionKill/{cdProfile}")
	public ResponseEntity getChampionKillByProfile(@PathVariable String cdProfile) {
		return getChampionKillByProfile(cdProfile, TypeEnum.KILL.getType());
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, value="/getChampionDeath/{cdProfile}")
	public ResponseEntity getChampionDeathByProfile(@PathVariable String cdProfile) {
		return getChampionKillByProfile(cdProfile, TypeEnum.DEATH.getType());
	}

	private ResponseEntity getChampionKillByProfile(@PathVariable String cdProfile, String deType) {
		KillServiceImpl killServiceImpl = new KillServiceImpl();
		List<ChampionStatisticsDTO> listMostKilledChampions= new ArrayList<>();

		if(TypeEnum.isKill(deType)) {
			listMostKilledChampions = killServiceImpl.findMostKilledChampionsByProfile(championService, cdProfile);
		}else if(TypeEnum.isDeath(deType)) {
			listMostKilledChampions= killServiceImpl.findMostDeathToChampionsByProfile(championService, cdProfile);
		}

		if(listMostKilledChampions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(listMostKilledChampions, HttpStatus.OK);
	}


}
