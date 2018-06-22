package com.junior.personalstats.model.dto;

import java.io.Serializable;

public class HeaderStatisticsDTO implements Serializable{

	private Integer nuKills;
	private Integer nuDeaths;
	private Integer nuAssists;
	private Integer nuGames;
	private Integer nuDoubleKills;
	private Integer nuTripleKills;
	private Integer nuQuadraKills;
	private Integer nuPentaKills;

	public HeaderStatisticsDTO() {
		//hide constructor
	}

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

	public Integer getNuDoubleKills() {
		return nuDoubleKills;
	}

	public void setNuDoubleKills(Integer nuDoubleKills) {
		this.nuDoubleKills = nuDoubleKills;
	}

	public Integer getNuTripleKills() {
		return nuTripleKills;
	}

	public void setNuTripleKills(Integer nuTripleKills) {
		this.nuTripleKills = nuTripleKills;
	}

	public Integer getNuQuadraKills() {
		return nuQuadraKills;
	}

	public void setNuQuadraKills(Integer nuQuadraKills) {
		this.nuQuadraKills = nuQuadraKills;
	}

	public Integer getNuPentaKills() {
		return nuPentaKills;
	}

	public void setNuPentaKills(Integer nuPentaKills) {
		this.nuPentaKills = nuPentaKills;
	}

}
