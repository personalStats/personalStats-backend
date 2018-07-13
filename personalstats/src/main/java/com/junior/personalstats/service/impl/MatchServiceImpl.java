package com.junior.personalstats.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.stereotype.Service;

import com.junior.personalstats.model.Match;
import com.junior.personalstats.model.dto.GenericDragonDTO;
import com.junior.personalstats.model.dto.GenericStatsDTO;
import com.junior.personalstats.model.dto.HeaderStatisticsDTO;
import com.junior.personalstats.model.dto.RankingStatsDTO;
import com.junior.personalstats.repository.MatchRepository;
import com.junior.personalstats.service.MatchService;
import com.junior.personalstats.util.MongoHandler;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

@Service
public class MatchServiceImpl extends MongoHandler implements MatchService {

	@Autowired
	private MatchRepository matchRepository;

	@Override
	public void save(Match match) {
		matchRepository.save(match);
	}

	@Override
	public HeaderStatisticsDTO findHeaderStatisticsByProfile(String cdProfile){

		MongoClient mongoClient = createMongoConnection();
		try {

			GroupOperation group = Aggregation.group().sum("nuDoubleKills").as("nuDoubleKills").sum("nuTripleKills").as("nuTripleKills").sum("nuQuadraKills").as("nuQuadraKills").sum("nuPentaKills").as("nuPentaKills")
													  .sum("nuKills").as("nuKills").sum("nuDeaths").as("nuDeaths").sum("nuAssists").as("nuAssists").count().as("nuGames");

			ProjectionOperation projectToMatchModel = Aggregation.project()
					.andExpression("nuGames").as("nuGames")
					.andExpression("nuDoubleKills").as("nuDoubleKills")
					.andExpression("nuTripleKills").as("nuTripleKills")
					.andExpression("nuQuadraKills").as("nuQuadraKills")
					.andExpression("nuPentaKills").as("nuPentaKills")
					.andExpression("nuKills").as("nuKills")
					.andExpression("nuDeaths").as("nuDeaths")
					.andExpression("nuAssists").as("nuAssists");

			Aggregation aggregation = Aggregation.newAggregation(group, projectToMatchModel);

			AggregationResults<HeaderStatisticsDTO> results = createMongoTemplate().aggregate(aggregation, "match", HeaderStatisticsDTO.class);
			return results.getUniqueMappedResult();

		}finally {
			mongoClient.close();
		}

	}

	@Override
	public List<HeaderStatisticsDTO> findKillingspreePerChampionByProfile(String cdProfile) {

		MongoClient mongoClient = createMongoConnection();
		try {

			GroupOperation group = Aggregation.group("nuChampion").sum("nuDoubleKills").as("nuDoubleKills").sum("nuTripleKills").as("nuTripleKills").sum("nuQuadraKills").as("nuQuadraKills").sum("nuPentaKills").as("nuPentaKills")
													  .sum("nuKills").as("nuKills").sum("nuDeaths").as("nuDeaths").sum("nuAssists").as("nuAssists").first("nuChampion").as("nuChampion").count().as("nuGames");

			ProjectionOperation projectToMatchModel = Aggregation.project()
					.andExpression("nuGames").as("nuGames")
					.andExpression("nuChampion").as("nuChampion")
					.andExpression("nuDoubleKills").as("nuDoubleKills")
					.andExpression("nuTripleKills").as("nuTripleKills")
					.andExpression("nuQuadraKills").as("nuQuadraKills")
					.andExpression("nuPentaKills").as("nuPentaKills")
					.andExpression("nuKills").as("nuKills")
					.andExpression("nuDeaths").as("nuDeaths")
					.andExpression("nuAssists").as("nuAssists");

			Aggregation aggregation = Aggregation.newAggregation(group, projectToMatchModel);

			AggregationResults<HeaderStatisticsDTO> results = createMongoTemplate().aggregate(aggregation, "match", HeaderStatisticsDTO.class);
			return results.getMappedResults();

		}finally {
			mongoClient.close();
		}
	}

	@Override
	public List<GenericStatsDTO> findVisionScoreById(Integer cdProfile) {
		List<GenericStatsDTO> listVisionScore = new ArrayList<>();
		MongoClient mongoClient = createMongoConnection();
		MongoDatabase database = mongoClient.getDatabase("personalStats");

		try {
			MongoCollection<Document> collection = database.getCollection("match");

			MongoCursor<Document> iterator = collection.find(
					//Filters.eq("cdProfile", cdProfile)
					).sort(Sorts.descending("nuVisionScore"))
					.iterator();


			while(iterator.hasNext()) {
			     Document doc = iterator.next();
			     listVisionScore.add(new GenericStatsDTO( doc.getInteger("gameId"), doc.getInteger("nuVisionScore")));
			}

			return listVisionScore;
		}finally {
			mongoClient.close();
		}
	}

	@Override
	public GenericDragonDTO findDragonsByProfile(String cdProfile) {
		MongoClient mongoClient = createMongoConnection();
		MongoDatabase database = mongoClient.getDatabase("personalStats");

		try {
			MongoCollection<Document> collection = database.getCollection("match");

			MongoCursor<Document> iterator = collection.find().iterator();

			List<GenericStatsDTO> listDragons = new ArrayList<>();
			while(iterator.hasNext()) {
			     Document doc = iterator.next();
			     listDragons.add(new GenericStatsDTO(doc.getInteger("nuGameId"), doc.getInteger("nuDragons"), doc.getBoolean("isWin")));
			}

			return mountObjectDragon(listDragons);
		}finally {
			mongoClient.close();
		}

	}

	private GenericDragonDTO mountObjectDragon(List<GenericStatsDTO> listDragons) {
		long nuGamesWithAtLeastOneDrag = listDragons.stream().filter(c -> c.getNuValor() > 0).count();
		long nuGamesWinWithAtLeastOneDrag = listDragons.stream().filter(c -> c.getNuValor() > 0).filter(c->c.isWin()).count();

		long nuGamesWithMorethen2Drags = listDragons.stream().filter(c-> c.getNuValor() >= 2).count();
		long nuGamesWithMorethen2DragsAndWin = listDragons.stream().filter(c-> c.getNuValor() >= 2).filter(c -> c.isWin()).count();

		int nuTotalDragons = listDragons.stream().map(GenericStatsDTO::getNuValor).mapToInt(Integer::intValue).sum();
		long nuDragonAvgPerGame =  nuTotalDragons / listDragons.size();

		long nuTotalGames = listDragons.size();
		GenericDragonDTO genericDragonDTO = new GenericDragonDTO();
		genericDragonDTO.setNuTotalDragons(nuTotalDragons);
		genericDragonDTO.setNuDragonAvgPerGame(nuDragonAvgPerGame);
		genericDragonDTO.setNuGamesWithAtLeastOneDrag(nuGamesWithAtLeastOneDrag);
		genericDragonDTO.setNuGamesWithAtLeastOneDragPerc(calcPercent(nuGamesWithAtLeastOneDrag, nuTotalGames));
		genericDragonDTO.setNuGamesWinWithAtLeastOneDrag(nuGamesWinWithAtLeastOneDrag);
		genericDragonDTO.setNuGamesWinWithAtLeastOneDragPerc(calcPercent(nuGamesWinWithAtLeastOneDrag, nuTotalGames));
		genericDragonDTO.setNuGamesWithMorethen2Drags(nuGamesWithMorethen2DragsAndWin);
		genericDragonDTO.setNuGamesWithMorethen2DragsPerc(calcPercent(nuGamesWithMorethen2Drags, nuTotalGames));
		genericDragonDTO.setNuGamesWithMorethen2DragsAndWin(nuGamesWithMorethen2DragsAndWin);
		genericDragonDTO.setNuGamesWithMorethen2DragsAndWinPerc(calcPercent(nuGamesWithMorethen2DragsAndWin, nuTotalGames));
		return genericDragonDTO;
	}

	private Long calcPercent(long value, Long nuTotalGames){
		return value * 100 / nuTotalGames;
	}


	@Override
	public List<RankingStatsDTO> findRankingDragons(){

		List<RankingStatsDTO> listRankingDrag = new ArrayList<>();
		MongoClient mongoClient = createMongoConnection();
		MongoDatabase database = mongoClient.getDatabase("personalStats");

		try{

			MongoCollection<Document> collection = database.getCollection("match");

			MongoCursor<Document> iterator = collection.aggregate(
					Arrays.asList(
							Aggregates.group("$profile.cdProfile", Accumulators.sum("total", "$nuDragons")),
							Aggregates.match(Filters.gt("$nuDragons", 0)),
							Aggregates.sort(Sorts.descending("total"))
					)
			)
			.iterator();

			Integer contador = 1;
			while(iterator.hasNext()) {
			     Document doc = iterator.next();
			     listRankingDrag.add(new RankingStatsDTO(contador, doc.getString("profile.nmProfile"), doc.getInteger("total")));
			}

			return listRankingDrag;
		}finally {
			mongoClient.close();
		}
	}






}
