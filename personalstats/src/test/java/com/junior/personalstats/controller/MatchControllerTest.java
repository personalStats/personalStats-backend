package com.junior.personalstats.controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.junior.personalstats.model.dto.ChampionStatisticsDTO;
import com.junior.personalstats.model.dto.GenericDragonDTO;
import com.junior.personalstats.model.dto.GenericStatsDTO;
import com.junior.personalstats.model.dto.HeaderStatisticsDTO;
import com.junior.personalstats.model.dto.ParticipantDTO;
import com.junior.personalstats.service.ChampionService;
import com.junior.personalstats.service.impl.DragonServiceImpl;
import com.junior.personalstats.service.impl.KillServiceImpl;
import com.junior.personalstats.service.impl.MatchServiceImpl;

public class MatchControllerTest {

	@Autowired
	private ChampionService championService;

	@Test
	public void searchForData() {
		String cdProfile = "1";
		KillServiceImpl killRepository = new KillServiceImpl();

		System.out.println("=========== KILL =================");
		List<ChampionStatisticsDTO> kills = killRepository.findMostKilledChampionsByProfile(championService, cdProfile);
		kills.stream().forEach(s-> {
			System.out.println(String.format("NuChampion %s : %s kills", s.getNuChampion(), s.getNuKills()));
		});

		System.out.println("=========== DEATHS =================");
		List<ChampionStatisticsDTO> deaths = killRepository.findMostDeathToChampionsByProfile(championService, cdProfile);
		deaths.stream().forEach(s-> {
			System.out.println(String.format("NuChampion %s : %s deaths", s.getNuChampion(), s.getNuKills()));
		});
	}

	@Test
	public void searchMultipleKills() {
		String cdProfile = "1";
		MatchServiceImpl matchServiceImpl = new MatchServiceImpl();

		HeaderStatisticsDTO headerStatisticsDTO = matchServiceImpl.findHeaderStatisticsByProfile(cdProfile);

		System.out.println("=========== DOUBLE KILL =================");
		System.out.println(headerStatisticsDTO.getNuDoubleKills());
		System.out.println("=========== TRIPLE KILL =================");
		System.out.println(headerStatisticsDTO.getNuTripleKills());
		System.out.println("=========== QUADRA KILL =================");
		System.out.println(headerStatisticsDTO.getNuQuadraKills());
		System.out.println("=========== PENTA KILL =================");
		System.out.println(headerStatisticsDTO.getNuPentaKills());
	}

	@Test
	public void searchVisionScore() {
		Integer cdProfile = 1;
		MatchServiceImpl matchServiceImpl = new MatchServiceImpl();

		List<GenericStatsDTO> listVisionScore = matchServiceImpl.findVisionScoreById(cdProfile);
		int totalVisionScore = 0;
		int totalGames = 0;
		for (GenericStatsDTO genericStatsDTO : listVisionScore) {
			totalVisionScore += genericStatsDTO.getNuValor();
			System.out.println(String.format("GameId %s : %s vision score", genericStatsDTO.getNuGameId(), genericStatsDTO.getNuValor()));
			totalGames++;
		}

		System.out.println(String.format("Adverage vision score: %s in %s games", totalVisionScore/totalGames, totalGames));

	}

	@Test
	public void searchDragonScore() {
		String cdProfile = "1";
		DragonServiceImpl dragonServiceImpl = new DragonServiceImpl();

		GenericDragonDTO genericDragonDTO = dragonServiceImpl.findDragonsByProfile(cdProfile);

		System.out.println("Média de dragões por game: " + genericDragonDTO.getNuDragonAvgPerGame());
		System.out.println(String.format("Games com pelo menos 1 dragao: %s (%s dos jogos)", genericDragonDTO.getNuGamesWithAtLeastOneDrag(), genericDragonDTO.getNuGamesWithAtLeastOneDragPerc()));
		System.out.println(String.format("Games com pelo menos 1 dragao e com vitoria: %s (%s dos jogos) ", genericDragonDTO.getNuGamesWinWithAtLeastOneDrag(), genericDragonDTO.getNuGamesWinWithAtLeastOneDragPerc()));
		System.out.println(String.format("Games com pelo menos 2 dragoes: %s (%s dos jogos) ", genericDragonDTO.getNuGamesWithMorethen2Drags(), genericDragonDTO.getNuGamesWithMorethen2DragsPerc()));
		System.out.println(String.format("Games com pelo menos 2 dragoes e com vitoria: %s (%s dos jogos) ", genericDragonDTO.getNuGamesWithMorethen2DragsAndWin(), genericDragonDTO.getNuGamesWithMorethen2DragsAndWinPerc()));
	}

	@Test
	public void testaArrayObject() {
		Integer nuTeamtoFilter = 2;
		Integer nuParticipanttoFilter = 4;

		List<ParticipantDTO> listaPart = new ArrayList<>();
		listaPart.add(new ParticipantDTO(16, 1, null, 6));
		listaPart.add(new ParticipantDTO(15, 1, null, 5));
		listaPart.add(new ParticipantDTO(14, 2, null, 4));
		listaPart.add(new ParticipantDTO(13, 2, null, 3));
		listaPart.add(new ParticipantDTO(12, 2, null, 2));

		boolean isPresent = listaPart.stream().filter(p -> p.getNuTeam() == nuTeamtoFilter).anyMatch(p -> p.getNuParticipant() == nuParticipanttoFilter);
		assertTrue(isPresent);
	}



}

