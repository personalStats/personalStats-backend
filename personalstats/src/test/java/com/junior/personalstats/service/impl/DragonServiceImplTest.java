package com.junior.personalstats.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.junior.personalstats.model.dto.ChampionDTO;
import com.junior.personalstats.model.dto.RankingStatsDTO;

public class DragonServiceImplTest {


	@Test
	public void testChampionFromId() {

		DragonServiceImpl dragonServiceImpl = new DragonServiceImpl();

		List<RankingStatsDTO> findRankingDragons = dragonServiceImpl.findRankingDragons();

		System.out.println(findRankingDragons.size());

		assertThat(findRankingDragons).isNotEmpty();

	}

}
