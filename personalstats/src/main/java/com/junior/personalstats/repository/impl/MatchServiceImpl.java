package com.junior.personalstats.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.junior.personalstats.constants.KillingspreeEnum;
import com.junior.personalstats.constants.TypeEnum;
import com.junior.personalstats.model.dto.GenericStatsDTO;
import com.junior.personalstats.model.dto.HeaderStatisticsDTO;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

public class MatchServiceImpl {

	private MongoClient createMongoConnection() {
		return new MongoClient("localhost", 27017);
	}

	public HeaderStatisticsDTO findHeaderStatisticsByProfile(Integer cdProfile) {
		HeaderStatisticsDTO headerStatisticsDTO = new HeaderStatisticsDTO();
		headerStatisticsDTO.setNuDoubleKills(findKillingspreeByProfile(cdProfile, KillingspreeEnum.DOUBLE_KILL.getType()));
		headerStatisticsDTO.setNuTripleKills(findKillingspreeByProfile(cdProfile, KillingspreeEnum.TRIPLE_KILL.getType()));
		headerStatisticsDTO.setNuQuadraKills(findKillingspreeByProfile(cdProfile, KillingspreeEnum.QUADRA_KILL.getType()));
		headerStatisticsDTO.setNuPentaKills(findKillingspreeByProfile(cdProfile, KillingspreeEnum.PENTA_KILL.getType()));
		headerStatisticsDTO.setNuKills(findKillStatistics(cdProfile, TypeEnum.KILL.getType()));
		headerStatisticsDTO.setNuDeaths(findKillStatistics(cdProfile, TypeEnum.DEATH.getType()));
		headerStatisticsDTO.setNuAssists(findKillStatistics(cdProfile, TypeEnum.ASSIST.getType()));

		return headerStatisticsDTO;
	}

	private Integer findKillingspreeByProfile(Integer cdProfile, String deType) {
		//TODO: TRANSFORMAR EM 1 SELECT SOH. E NAO 1 POR TIPO

		MongoClient mongoClient = createMongoConnection();
		MongoDatabase database = mongoClient.getDatabase("personalStats");

		try{

			MongoCollection<Document> collection = database.getCollection("match");

			MongoCursor<Document> iterator = collection.aggregate(
					Arrays.asList(
							//Aggregates.match(Filters.eq("cdProfile", cdProfile)),
							Aggregates.group(getColumnFromType(deType), Accumulators.sum("count", getColumnFromType(deType))),
							Aggregates.match(Filters.gt("count", 0)),
							Aggregates.sort(Sorts.descending("count"))
					)
			)
			.iterator();

			Integer contador = 0;
			while(iterator.hasNext()) {
			     Document doc = iterator.next();
			     contador = doc.getInteger("count");
			}

			return contador;
		}finally {
			mongoClient.close();
		}

	}


	private String getColumnFromType(String deType) {
		if(KillingspreeEnum.isDoubleKill(deType)) {
			return "$nuDoubleKills";
		}else if(KillingspreeEnum.isTripleKill(deType)) {
			return "$nuTripleKills";
		}else if(KillingspreeEnum.isQuadraKill(deType)) {
			return "$nuQuadraKills";
		}else if(KillingspreeEnum.isPentaKill(deType)) {
			return "$nuPentaKills";
		}else if (TypeEnum.isKill(deType)) {
			return "$nuKills";
		}else if (TypeEnum.isDeath(deType)) {
			return "$nuDeaths";
		}else if (TypeEnum.isAssist(deType)) {
			return "$nuAssists";
		}

		return null;
	}

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
			     listVisionScore.add(new GenericStatsDTO( doc.getString("gameId"), doc.getInteger("nuVisionScore")));
			}

			return listVisionScore;
		}finally {
			mongoClient.close();
		}
	}

	private Integer findKillStatistics(Integer cdProfile, String deType) {
		MongoClient mongoClient = createMongoConnection();
		MongoDatabase database = mongoClient.getDatabase("personalStats");

		try {
			MongoCollection<Document> collection = database.getCollection("match");

			MongoCursor<Document> iterator = collection.aggregate(
					Arrays.asList(
							//Aggregates.match(Filters.eq("cdProfile", cdProfile)),
							Aggregates.group(deType, Accumulators.sum("count", getColumnFromType(deType))),
							Aggregates.sort(Sorts.descending("count"))
					)
			)
			.iterator();

			Integer contador = 0;
			while(iterator.hasNext()) {
			     Document doc = iterator.next();
			     contador = doc.getInteger("count");
			}

			return contador;
		}finally {
			mongoClient.close();
		}

	}

}
