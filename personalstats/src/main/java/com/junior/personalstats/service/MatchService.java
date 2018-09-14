package com.junior.personalstats.service;

import java.util.List;

import com.junior.personalstats.model.Match;
import com.junior.personalstats.model.dto.GenericDragonDTO;
import com.junior.personalstats.model.dto.GenericStatsDTO;
import com.junior.personalstats.model.dto.HeaderStatisticsDTO;
import com.junior.personalstats.model.dto.RankingStatsDTO;

public interface MatchService {

	void save(Match match);

	HeaderStatisticsDTO findHeaderStatisticsByProfile(String cdProfile);

	List<HeaderStatisticsDTO> findKillingspreePerChampionByProfile(String cdProfile);

	List<GenericStatsDTO> findVisionScoreById(Integer cdProfile);

	List<GenericStatsDTO> findMostDamagedGameByProfile(String cdProfile);

}
