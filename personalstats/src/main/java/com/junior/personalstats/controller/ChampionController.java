package com.junior.personalstats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.junior.personalstats.model.dto.ChampionDTO;
import com.junior.personalstats.service.ChampionService;

@RestController
public class ChampionController {

	@Autowired
	private ChampionService championService;

    @RequestMapping(method = RequestMethod.GET, path="/champions/{nuChampion}")
    public ChampionDTO getChampionById(@PathVariable(name = "nuChampion") Integer nuChampion) {
    	return championService.getChampionById(nuChampion);
    }

}
