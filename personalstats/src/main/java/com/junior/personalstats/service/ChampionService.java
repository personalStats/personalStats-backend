package com.junior.personalstats.service;

import com.junior.personalstats.model.dto.ChampionDTO;

public interface ChampionService {

	ChampionDTO getChampionById(Integer nuChampion);

	void putAllChampionsOnCache();

}
