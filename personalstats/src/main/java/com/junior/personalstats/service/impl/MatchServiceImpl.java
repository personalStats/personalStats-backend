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

import com.junior.personalstats.constants.TypeEnum;
import com.junior.personalstats.model.Match;
import com.junior.personalstats.model.dto.ChampionDTO;
import com.junior.personalstats.model.dto.ChampionStatisticsDTO;
import com.junior.personalstats.model.dto.GenericStatsDTO;
import com.junior.personalstats.model.dto.HeaderStatisticsDTO;
import com.junior.personalstats.repository.MatchRepository;
import com.junior.personalstats.service.ChampionService;
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

	@Autowired
	private ChampionService championService;

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
			HeaderStatisticsDTO headerStatisticsDTO = results.getUniqueMappedResult();
			headerStatisticsDTO.setChampion(championService.getChampionById(headerStatisticsDTO.getNuChampion()));
			return headerStatisticsDTO;

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
			List<HeaderStatisticsDTO> listHeader = results.getMappedResults();
			for (HeaderStatisticsDTO headerStatisticsDTO : listHeader) {
				headerStatisticsDTO.setChampion(championService.getChampionById(headerStatisticsDTO.getNuChampion()));
			}

			return listHeader;

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
	public List<GenericStatsDTO> findMostDamagedGameByProfile(String cdProfile){
		return null;
	}


}
