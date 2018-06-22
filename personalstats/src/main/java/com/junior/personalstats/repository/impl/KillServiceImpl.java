package com.junior.personalstats.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.junior.personalstats.constants.TypeEnum;
import com.junior.personalstats.model.dto.ChampionStatisticsDTO;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

public class KillServiceImpl {

	public List<ChampionStatisticsDTO> findMostKilledChampionsByProfile(String cdProfile) {
		 return findDataByProfile(cdProfile, TypeEnum.KILL.getType());
	}

	public List<ChampionStatisticsDTO> findMostDeathToChampionsByProfile(String cdProfile) {
		 return findDataByProfile(cdProfile, TypeEnum.DEATH.getType());
	}

	public List<ChampionStatisticsDTO> findDataByProfile(String cdProfile, String deType) {
		List<ChampionStatisticsDTO> listaKills = new ArrayList<ChampionStatisticsDTO>();

		MongoDatabase database = createMongoConnection();

		MongoCollection<Document> collection = database.getCollection("kill");

		MongoCursor<Document> iterator = collection.aggregate(
				Arrays.asList(
						Aggregates.match(Filters.eq("type", deType)),
						Aggregates.group(deType.equals(TypeEnum.KILL.getType()) ? "$nuChampionKill" : "$nuChampionDeath", Accumulators.sum("count", 1)),
						Aggregates.sort(Sorts.descending("count"))
				)
		)
		.iterator();
		while(iterator.hasNext()) {
		     Document doc = iterator.next();
		     listaKills.add(new ChampionStatisticsDTO(doc.getInteger("_id" ), doc.getInteger("count")));
		}

		return listaKills;

	}

	private MongoDatabase createMongoConnection() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		return mongoClient.getDatabase("personalStats");
	}

	 Block<Document> printBlock = new Block<Document>() {
	        @Override
	        public void apply(final Document document) {
	            System.out.println(document.toJson());
	        }
	    };



}
