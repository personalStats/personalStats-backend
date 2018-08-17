package com.junior.personalstats;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.junior.personalstats.service.ChampionService;

@Component
public class FillCacheableData {

	protected static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private ChampionService championService;

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		LOGGER.info("Starting API to put champions on cache...");

		championService.putAllChampionsOnCache();

		LOGGER.info("Ending API to put champions on cache...");
	}
}