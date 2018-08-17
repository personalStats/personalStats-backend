package com.junior.personalstats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.junior.personalstats.service.ChampionService;

@SpringBootApplication
@EnableCaching
public class PersonalstatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalstatsApplication.class, args);
	}
}
