package com.junior.personalstats.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.junior.personalstats.constants.SortEnum;
import com.junior.personalstats.model.dto.ChampionDTO;
import com.junior.personalstats.model.dto.GeneralDataDTO;
import com.junior.personalstats.service.ChampionService;
import com.junior.personalstats.service.GeneralDataService;
import com.junior.personalstats.util.MongoHandler;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

@Service
public class GeneralDataServiceImpl extends MongoHandler implements GeneralDataService{

	@Override
	public List<GeneralDataDTO> findGamesWithBestCriticalStrike(ChampionService championService, String cdProfile) {
		List<GeneralDataDTO> listGames = new ArrayList<>();

		MongoCursor<Document> iterator = getMatchDetailsByProfile(cdProfile);
		
		while(iterator.hasNext()) {
		     Document doc = iterator.next();
		     if(gameHaveACritical(doc)) { 
			     ChampionDTO championDTO = championService.getChampionById(doc.getInteger("nuChampion"));
			     listGames.add(new GeneralDataDTO(doc.getInteger("nuGameId"), championDTO, doc.getInteger("nuMaxCriticalStrike"), doc.getDate("dtMatch"), doc.getBoolean("isWin"), null, null));
		     }
		}
		
		return listGames; 
	}

	private MongoCursor<Document> getMatchDetailsByProfile(String cdProfile) {
		MongoClient mongoClient = createMongoConnection();

		MongoCollection<Document> collection = mongoClient.getDatabase("personalStats").getCollection("match");

		return collection.find(
				Filters.and(
					Filters.eq("profile.deProfile", cdProfile),
					Filters.gt("nuGameLength", 300)
				)).sort(Sorts.descending("nuMaxCriticalStrike"))
				.iterator();
	}
	
	private boolean gameHaveACritical(Document doc) {
		return doc.getInteger("nuMaxCriticalStrike") != null && doc.getInteger("nuMaxCriticalStrike")  > 0;
	}
	
	private boolean gameHaveDmgDealt(Document doc) {
		return doc.getLong("nuDmgDealt") != null && doc.getLong("nuDmgDealt")  > 0;
	}

	private boolean gameHaveGameLength(Document doc) {
		return doc.getInteger("nuGameLength") != null && doc.getInteger("nuGameLength")  > 0;
	}

//	
//	private List<GeneralDataDTO> orderListByGreaterCrititalStrike(List<GeneralDataDTO> listGames) {
//		return listGames.stream().sorted(Comparator.comparing(GeneralDataDTO::getNuCriticalStrike).reversed()).collect(Collectors.toList());
//	}
	
	@Override
	public List<GeneralDataDTO> findGreaterDamagePerMatch(ChampionService championService, String cdProfile) {
		List<GeneralDataDTO> listGames = new ArrayList<>();

		MongoClient mongoClient = createMongoConnection();

		MongoCollection<Document> collection = mongoClient.getDatabase("personalStats").getCollection("match");

		MongoCursor<Document> iterator = collection.find(
				Filters.and(
					Filters.eq("profile.deProfile", cdProfile),
					Filters.gt("nuGameLength", 300)
				)).sort(Sorts.descending("nuDmgDealt"))
				.iterator();
		
		while(iterator.hasNext()) {
		     Document doc = iterator.next();
		     if(gameHaveDmgDealt(doc)) { 
			     ChampionDTO championDTO = championService.getChampionById(doc.getInteger("nuChampion"));
			     listGames.add(new GeneralDataDTO(doc.getInteger("nuGameId"), championDTO, null, doc.getDate("dtMatch"), doc.getBoolean("isWin"), doc.getLong("nuDmgDealt"), null));
		     }
		}
		
		return listGames; 
	}
	
	@Override
	public List<GeneralDataDTO> findLongerMatchs(ChampionService championService, String cdProfile) {
		return findGamesPerLength(championService, cdProfile, SortEnum.DESC.getType()); 
	}
	
	@Override
	public List<GeneralDataDTO> findFasterMatchs(ChampionService championService, String cdProfile) {
		return findGamesPerLength(championService, cdProfile, SortEnum.ASC.getType()); 
	}

	private List<GeneralDataDTO> findGamesPerLength(ChampionService championService, String cdProfile, String deType) {
		List<GeneralDataDTO> listGames = new ArrayList<>();

		MongoClient mongoClient = createMongoConnection();

		MongoCollection<Document> collection = mongoClient.getDatabase("personalStats").getCollection("match");

		MongoCursor<Document> iterator = collection.find(
				Filters.and(
						Filters.eq("profile.deProfile", cdProfile),
						Filters.gt("nuGameLength", 300)
				)).sort(SortEnum.isSortAsc(deType) ? Sorts.ascending("nuGameLength") : Sorts.descending("nuGameLength"))
				.iterator();
		
		while(iterator.hasNext()) {
		     Document doc = iterator.next();
		     if(gameHaveGameLength(doc)) { 
			     ChampionDTO championDTO = championService.getChampionById(doc.getInteger("nuChampion"));
			     listGames.add(new GeneralDataDTO(doc.getInteger("nuGameId"), championDTO, null, doc.getDate("dtMatch"), doc.getBoolean("isWin"), null, doc.getInteger("nuGameLength")));
		     }
		}
		
		return listGames;
	}

}
