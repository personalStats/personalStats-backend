package com.junior.personalstats.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junior.personalstats.constants.TypeEnum;
import com.junior.personalstats.model.Kill;
import com.junior.personalstats.model.dto.ChampionDTO;
import com.junior.personalstats.model.dto.ChampionStatisticsDTO;
import com.junior.personalstats.repository.KillRepository;
import com.junior.personalstats.service.ChampionService;
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
	private KillRepository killRepository;

	private ChampionService championService;

	@Override
	public void save(Kill kill) {
		killRepository.save(kill);
	}


	@Override
	public List<ChampionStatisticsDTO> findMostKilledChampionsByProfile(ChampionService championService, String cdProfile) {
		this.championService = championService;
		return findDataByProfile(cdProfile, TypeEnum.KILL.getType());
	}

	@Override
	public List<ChampionStatisticsDTO> findMostDeathToChampionsByProfile(ChampionService championService, String cdProfile) {
		this.championService = championService;
		 return findDataByProfile(cdProfile, TypeEnum.DEATH.getType());
	}

	private List<ChampionStatisticsDTO> findDataByProfile(String cdProfile, String deType) {
		List<ChampionStatisticsDTO> listaKills = new ArrayList<>();

		MongoClient mongoClient = createMongoConnection();

		MongoCollection<Document> collection = mongoClient.getDatabase("personalStats").getCollection("kill");

		MongoCursor<Document> iterator = collection.aggregate(
				Arrays.asList(
						Aggregates.match(Filters.eq("type", deType)),
						Aggregates.group(deType.equals(TypeEnum.KILL.getType()) ?  "$nuChampionDeath" : "$nuChampionKill" , Accumulators.sum("count", 1)),
						Aggregates.sort(Sorts.descending("count"))
				)
		)
		.iterator();
		while(iterator.hasNext()) {
		     Document doc = iterator.next();
		     ChampionDTO championDTO = championService.getChampionById(doc.getInteger("_id" ));
		     listaKills.add(new ChampionStatisticsDTO(championDTO, doc.getInteger("count")));
		}

		return listaKills;

	}

}
