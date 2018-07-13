package com.junior.personalstats.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junior.personalstats.constants.TypeEnum;
import com.junior.personalstats.model.Kill;
import com.junior.personalstats.model.dto.ChampionStatisticsDTO;
import com.junior.personalstats.repository.KillRepository;
import com.junior.personalstats.service.KillService;
import com.junior.personalstats.util.MongoHandler;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

@Service
public class KillServiceImpl extends MongoHandler implements KillService{

	@Autowired
	private KillRepository KillRepository;

	@Override
	public void save(Kill kill) {
		KillRepository.save(kill);
	}


	@Override
	public List<ChampionStatisticsDTO> findMostKilledChampionsByProfile(String cdProfile) {
		 return findDataByProfile(cdProfile, TypeEnum.KILL.getType());
	}

	@Override
	public List<ChampionStatisticsDTO> findMostDeathToChampionsByProfile(String cdProfile) {
		 return findDataByProfile(cdProfile, TypeEnum.DEATH.getType());
	}

	private List<ChampionStatisticsDTO> findDataByProfile(String cdProfile, String deType) {
		List<ChampionStatisticsDTO> listaKills = new ArrayList<>();

		MongoClient mongoClient = createMongoConnection();

		MongoCollection<Document> collection = mongoClient.getDatabase("personalStats").getCollection("kill");

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

}
