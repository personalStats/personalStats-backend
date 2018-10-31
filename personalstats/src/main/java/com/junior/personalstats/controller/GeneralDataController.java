package com.junior.personalstats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.junior.personalstats.model.dto.GeneralDataDTO;
import com.junior.personalstats.service.ChampionService;
import com.junior.personalstats.service.GeneralDataService;

@RestController
public class GeneralDataController {

	@Autowired
	private GeneralDataService generalDataService;
	
	@Autowired
	private ChampionService championService; 
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, path="/bestCriticalStrikes/{cdProfile}")
	public ResponseEntity getBestCrititalStrikes(@PathVariable String cdProfile) {
		
		List<GeneralDataDTO> listGamesWithBestCritialStrike = generalDataService.findGamesWithBestCriticalStrike(championService, cdProfile);
		
		if(listGamesWithBestCritialStrike == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listGamesWithBestCritialStrike, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, path="/greaterDamagePerMatch/{cdProfile}")
	public ResponseEntity getGreaterDamagePerMatch(@PathVariable String cdProfile) {
		
		List<GeneralDataDTO> listGamesWithGreaterDamagePerMatch = generalDataService.findGreaterDamagePerMatch(championService, cdProfile);
		
		if(listGamesWithGreaterDamagePerMatch == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listGamesWithGreaterDamagePerMatch, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, path="/longerMatchs/{cdProfile}")
	public ResponseEntity getLongerMatchs(@PathVariable String cdProfile) {
		
		List<GeneralDataDTO> listLongerMatchs = generalDataService.findLongerMatchs(championService, cdProfile);
		
		if(listLongerMatchs == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listLongerMatchs, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, path="/fasterMatchs/{cdProfile}")
	public ResponseEntity getFasterMatchs(@PathVariable String cdProfile) {
		
		List<GeneralDataDTO> listFasterMatchs = generalDataService.findFasterMatchs(championService, cdProfile);
		
		if(listFasterMatchs == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listFasterMatchs, HttpStatus.OK);
	}
	
	
}
