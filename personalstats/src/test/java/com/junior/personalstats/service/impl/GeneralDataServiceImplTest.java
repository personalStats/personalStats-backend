package com.junior.personalstats.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.junior.personalstats.model.dto.GeneralDataDTO;
import com.junior.personalstats.service.ChampionService;
import com.junior.personalstats.service.GeneralDataService;

@RunWith(MockitoJUnitRunner.class)
public class GeneralDataServiceImplTest {

	private String cdProfile;
	
	@Before
	public void init() {
		cdProfile = "Papay";
	}
	
	@Test
	public void checkMyPositionInRanked() {
		GeneralDataServiceImpl generalDataService = new GeneralDataServiceImpl();
		ChampionServiceImpl championService = new ChampionServiceImpl();
		
		List<GeneralDataDTO> greaterDmgPerMatch = generalDataService.findMyPositionInRankedGreaterDamagePerMatch(championService, cdProfile, 0L);
		
		assertThat(greaterDmgPerMatch.size() > 0);
	}
	
	
}
