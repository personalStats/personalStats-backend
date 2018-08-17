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

import com.junior.personalstats.model.dto.GenericDragonDTO;
import com.junior.personalstats.model.dto.RankingStatsDTO;
import com.junior.personalstats.service.DragonService;

@RestController
@SuppressWarnings("rawtypes")
public class DragonController {

	@Autowired
	private DragonService dragonService;

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, value="/getDragonData/{cdProfile}")
	public ResponseEntity getDragonDataFromProfile(@PathVariable String cdProfile) {
		GenericDragonDTO dragonByProfile = dragonService.findDragonsByProfile(cdProfile);

		if(dragonByProfile == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(dragonByProfile, HttpStatus.OK);
	}


	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, value="/getDragonRankingData")
	public ResponseEntity getDragonRankingData() {
		List<RankingStatsDTO> listDragonRanking = dragonService.findRankingDragons();

		if(listDragonRanking.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(listDragonRanking, HttpStatus.OK);
	}

}
