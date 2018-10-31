package com.junior.personalstats.service;

import java.util.List;

import com.junior.personalstats.model.dto.ResourceDTO;

public interface ResourceService {

	List<ResourceDTO> findGamesWithBestAvgDmgPerResource(ChampionService championService, String cdProfile);

	List<ResourceDTO> findGamesWithBestAvgDmgByTime(ChampionService championService, String cdProfile);
	
}
