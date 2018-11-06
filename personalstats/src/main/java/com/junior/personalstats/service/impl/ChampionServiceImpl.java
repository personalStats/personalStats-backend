package com.junior.personalstats.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.junior.personalstats.model.dto.ChampionDTO;
import com.junior.personalstats.service.ChampionService;

@Service
public class ChampionServiceImpl implements ChampionService{

	private List<ChampionDTO> listaChampionDTO = new ArrayList<>();

	@Override
    public ChampionDTO getChampionById(Integer nuChampion) {
    	Optional<ChampionDTO> champFiltered = listaChampionDTO.stream().filter(championDTO -> championDTO.getNuChampion().equals(nuChampion)).findAny();
    	return champFiltered.isPresent() ? champFiltered.get() : null;
    }

	@Override
    @Cacheable(value="champions")
    public void putAllChampionsOnCache() {

		//TODO JUNIOR TROCAR PELA RIOT API
		String url = "http://ddragon.leagueoflegends.com/cdn/8.17.1/data/en_US/champion.json";

		ObjectNode objectNode = new RestTemplate().getForObject(url, ObjectNode.class);
		JsonNode highDepth = objectNode.get("data");
		for (JsonNode jsonNode : highDepth) {

			ChampionDTO championDTO = new ChampionDTO();
	    	championDTO.setNuChampion(jsonNode.get("key").asInt());
	    	championDTO.setDeChampion(jsonNode.get("id").asText());
	    	championDTO.setUrlFoto(jsonNode.get("image").get("full").asText());

	    	listaChampionDTO.add(championDTO);

		}
    }

}
