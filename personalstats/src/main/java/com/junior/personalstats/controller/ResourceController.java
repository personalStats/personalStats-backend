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

import com.junior.personalstats.model.dto.ResourceDTO;
import com.junior.personalstats.service.ChampionService;
import com.junior.personalstats.service.ResourceService;

@RestController
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private ChampionService championService; 
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, path="/dmgPerResource/{cdProfile}")
	public ResponseEntity getDmgPerResource(@PathVariable String cdProfile) {
		
		List<ResourceDTO> listGamesWithBestAvg = resourceService.findGamesWithBestAvgDmgPerResource(championService, cdProfile);
		
		if(listGamesWithBestAvg == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listGamesWithBestAvg, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, path="/dmgPerTime/{cdProfile}")
	public ResponseEntity getDmgPerTime(@PathVariable String cdProfile) {
		List<ResourceDTO> listGamesWithBestAvg = resourceService.findGamesWithBestAvgDmgByTime(championService, cdProfile);
		
		if(listGamesWithBestAvg == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listGamesWithBestAvg, HttpStatus.OK);
		
	}
	
	
}
