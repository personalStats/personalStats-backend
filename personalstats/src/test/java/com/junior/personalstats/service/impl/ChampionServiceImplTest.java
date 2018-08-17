package com.junior.personalstats.service.impl;

import org.junit.Assert;
import org.junit.Test;

import com.junior.personalstats.model.dto.ChampionDTO;

public class ChampionServiceImplTest {


	@Test
	public void testChampionFromId() {
		int nuChampionToTest = 12;

		ChampionServiceImpl championServiceImpl = new ChampionServiceImpl();

		championServiceImpl.putAllChampionsOnCache();

		ChampionDTO championToTest = championServiceImpl.getChampionById(nuChampionToTest);

		Assert.assertTrue(championToTest.getNuChampion() == 12);
		Assert.assertTrue(championToTest.getDeChampion().equalsIgnoreCase("Alistar"));
		Assert.assertTrue(championToTest.getUrlFoto().equalsIgnoreCase("Alistar.png"));


	}

}
