package com.junior.personalstats.controller;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Test;

import com.junior.personalstats.model.dto.ChampionStatisticsDTO;
import com.junior.personalstats.model.dto.GenericDragonDTO;
import com.junior.personalstats.model.dto.GenericStatsDTO;
import com.junior.personalstats.model.dto.HeaderStatisticsDTO;
import com.junior.personalstats.service.impl.KillServiceImpl;
import com.junior.personalstats.service.impl.MatchServiceImpl;

public class MatchControllerTest {

	@Test
	public void searchForData() {
		String cdProfile = "1";
		KillServiceImpl killRepository = new KillServiceImpl();

		System.out.println("=========== KILL =================");
		List<ChampionStatisticsDTO> kills = killRepository.findMostKilledChampionsByProfile(cdProfile);
		kills.stream().forEach(s-> {
			System.out.println(String.format("NuChampion %s : %s kills", s.getNuChampion(), s.getNuKills()));
		});

		System.out.println("=========== DEATHS =================");
		List<ChampionStatisticsDTO> deaths = killRepository.findMostDeathToChampionsByProfile(cdProfile);;
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
		MatchServiceImpl matchServiceImpl = new MatchServiceImpl();

		GenericDragonDTO genericDragonDTO = matchServiceImpl.findDragonsByProfile(cdProfile);

		System.out.println("Média de dragões por game: " + genericDragonDTO.getNuDragonAvgPerGame());
		System.out.println(String.format("Games com pelo menos 1 dragao: %s (%s dos jogos)", genericDragonDTO.getNuGamesWithAtLeastOneDrag(), genericDragonDTO.getNuGamesWithAtLeastOneDragPerc()));
		System.out.println(String.format("Games com pelo menos 1 dragao e com vitoria: %s (%s dos jogos) ", genericDragonDTO.getNuGamesWinWithAtLeastOneDrag(), genericDragonDTO.getNuGamesWinWithAtLeastOneDragPerc()));
		System.out.println(String.format("Games com pelo menos 2 dragoes: %s (%s dos jogos) ", genericDragonDTO.getNuGamesWithMorethen2Drags(), genericDragonDTO.getNuGamesWithMorethen2DragsPerc()));
		System.out.println(String.format("Games com pelo menos 2 dragoes e com vitoria: %s (%s dos jogos) ", genericDragonDTO.getNuGamesWithMorethen2DragsAndWin(), genericDragonDTO.getNuGamesWithMorethen2DragsAndWinPerc()));
	}



}

