package com.junior.personalstats.model.DTO;

import java.io.Serializable;

public class HeaderStatisticsDTO implements Serializable{

	private Integer nuKills;
	private Integer nuDeaths;
	private Integer nuAssists;
	private Integer nuGames;
	
	public HeaderStatisticsDTO(Integer nuKills, Integer nuDeaths, Integer nuAssists, Integer nuGames) {
		this.nuAssists = nuAssists;
		this.nuKills = nuKills;
		this.nuDeaths = nuDeaths;
		this.nuGames = nuGames;
	}

	public Integer getNuKills() {
		return nuKills;
	}
	public void setNuKills(Integer nuKills) {
		this.nuKills = nuKills;
	}
	public Integer getNuDeaths() {
		return nuDeaths;
	}
	public void setNuDeaths(Integer nuDeaths) {
		this.nuDeaths = nuDeaths;
	}
	public Integer getNuAssists() {
		return nuAssists;
	}
	public void setNuAssists(Integer nuAssists) {
		this.nuAssists = nuAssists;
	}

	public Integer getNuGames() {
		return nuGames;
	}

	public void setNuGames(Integer nuGames) {
		this.nuGames = nuGames;
	}
	
	
}
