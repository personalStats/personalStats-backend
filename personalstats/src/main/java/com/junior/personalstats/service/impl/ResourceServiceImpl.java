package com.junior.personalstats.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.junior.personalstats.model.dto.ChampionDTO;
import com.junior.personalstats.model.dto.ResourceDTO;
import com.junior.personalstats.service.ChampionService;
import com.junior.personalstats.service.ResourceService;
import com.junior.personalstats.util.MongoHandler;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

@Service
public class ResourceServiceImpl extends MongoHandler implements ResourceService{

	@Override
	public List<ResourceDTO> findGamesWithBestAvgDmgPerResource(ChampionService championService, String cdProfile) {
		List<ResourceDTO> listGames = new ArrayList<>();

		MongoCursor<Document> iterator = getMatchDetailsByProfile(cdProfile);
		
		while(iterator.hasNext()) {
		     Document doc = iterator.next();
		     if(gameIsNotARemake(doc)) { 
			     ChampionDTO championDTO = championService.getChampionById(doc.getInteger("nuChampion" ));
			     Long nuDmgDealt = doc.getLong("nuDmgDealt");
			     Integer nuGameLength = doc.getInteger("nuGameLength");
			     Integer nuGoldEarned = doc.getInteger("nuGoldEarned");
				 Double nuDmgPerGold = (double) nuDmgDealt / nuGoldEarned;
			     listGames.add(new ResourceDTO(championDTO, nuDmgPerGold, doc.getDate("dtMatch"), doc.getBoolean("isWin"), nuDmgDealt, nuGameLength, nuGoldEarned));
		     }
		}
		
		return orderListByMostDmgPerResource(listGames); 
	}

	private MongoCursor<Document> getMatchDetailsByProfile(String cdProfile) {
		MongoClient mongoClient = createMongoConnection();

		MongoCollection<Document> collection = mongoClient.getDatabase("personalStats").getCollection("match");

		MongoCursor<Document> iterator = collection.aggregate(
				Arrays.asList(
						Aggregates.match(Filters.eq("profile.deProfile", cdProfile))
				)
		)
		.iterator();
		return iterator;
	}
	
	@Override
	public List<ResourceDTO> findGamesWithBestAvgDmgByTime(ChampionService championService, String cdProfile){
		List<ResourceDTO> listGames = new ArrayList<>();
		
		MongoCursor<Document> iterator = getMatchDetailsByProfile(cdProfile);
		
		while(iterator.hasNext()) {
		     Document doc = iterator.next();
		     if(gameIsNotARemake(doc)) { 
			     ChampionDTO championDTO = championService.getChampionById(doc.getInteger("nuChampion" ));
			     
			     Long nuDmgDealt = doc.getLong("nuDmgDealt");
			     Integer nuGameLength = doc.getInteger("nuGameLength");
			     Integer nuGoldEarned = doc.getInteger("nuGoldEarned");
			     Double nuDmgPerTime = (double) doc.getLong("nuDmgDealt") / doc.getInteger("nuGameLength");
			     
			     listGames.add(new ResourceDTO(championDTO, nuDmgPerTime, doc.getDate("dtMatch"), doc.getBoolean("isWin"), nuDmgDealt, nuGameLength, nuGoldEarned ));
		     }
		}
		
		return orderListByMostDmgPerResource(listGames); 
		
		
	}

	private boolean gameIsNotARemake(Document doc) {
		return doc.getLong("nuDmgDealt") != null && doc.getInteger("nuGoldEarned") != null && doc.getInteger("nuGameLength") != null;
	}
	

	private List<ResourceDTO> orderListByMostDmgPerResource(List<ResourceDTO> listGames) {
		return listGames.stream().sorted(Comparator.comparing(ResourceDTO::getNuDmgPerGold).reversed()).collect(Collectors.toList());
	}

}
