package com.junior.personalstats.service;

import java.util.List;

import com.junior.personalstats.model.Kill;
import com.junior.personalstats.model.dto.ChampionStatisticsDTO;

public interface KillService {

	void save(Kill kill);

	List<ChampionStatisticsDTO> findMostKilledChampionsByProfile(String cdProfile);

	List<ChampionStatisticsDTO> findMostDeathToChampionsByProfile(String cdProfile);

}
