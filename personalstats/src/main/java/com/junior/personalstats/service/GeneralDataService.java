package com.junior.personalstats.service;

import java.util.List;

import com.junior.personalstats.model.dto.GeneralDataDTO;

public interface GeneralDataService {

	List<GeneralDataDTO> findGamesWithBestCriticalStrike(ChampionService championService, String cdProfile);

	List<GeneralDataDTO> findGreaterDamagePerMatch(ChampionService championService, String cdProfile);

	List<GeneralDataDTO> findLongerMatchs(ChampionService championService, String cdProfile);

	List<GeneralDataDTO> findFasterMatchs(ChampionService championService, String cdProfile);
	
}
