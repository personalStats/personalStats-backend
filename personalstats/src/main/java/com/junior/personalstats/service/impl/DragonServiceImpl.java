package com.junior.personalstats.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.stereotype.Service;

import com.junior.personalstats.model.Dragon;
import com.junior.personalstats.model.dto.GenericDragonDTO;
import com.junior.personalstats.model.dto.GenericStatsDTO;
import com.junior.personalstats.model.dto.RankingStatsDTO;
import com.junior.personalstats.repository.DragonRepository;
import com.junior.personalstats.service.DragonService;
import com.junior.personalstats.util.MongoHandler;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;

@Service
public class DragonServiceImpl extends MongoHandler implements DragonService{

	@Autowired
	private DragonRepository dragonRepostory;

	@Override
	public void save(Dragon dragon) {
		dragonRepostory.save(dragon);
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
							Aggregates.project(new Document("profile.deProfile", 1)),
							Aggregates.project(new Document("profile.nuAccount", 2)),
							Aggregates.group("$profile.nuAccount", Accumulators.sum("total", "$nuDragons")),
							Aggregates.sort(Sorts.descending("total"))
					)
			)
			.iterator();

			Integer contador = 0;
			while(iterator.hasNext()) {
			     Document doc = iterator.next();
			     listRankingDrag.add(new RankingStatsDTO(++contador, doc.getString("profile.deProfile"), doc.getInteger("total")));
			}

			return listRankingDrag;
		}finally {
			mongoClient.close();
		}
	}

}
