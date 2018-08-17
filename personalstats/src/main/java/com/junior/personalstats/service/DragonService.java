package com.junior.personalstats.service;

import java.util.List;

import com.junior.personalstats.model.Dragon;
import com.junior.personalstats.model.dto.GenericDragonDTO;
import com.junior.personalstats.model.dto.RankingStatsDTO;

public interface DragonService {

	void save(Dragon dragon);

	GenericDragonDTO findDragonsByProfile(String cdProfile);

	List<RankingStatsDTO> findRankingDragons();

}
