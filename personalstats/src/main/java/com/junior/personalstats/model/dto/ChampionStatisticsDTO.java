package com.junior.personalstats.model.dto;

import java.io.Serializable;

public class ChampionStatisticsDTO implements Serializable {

	private Integer nuChampion;
	private String nmChampion;
	private String urlPhoto;
	private Integer nuKills;
	private Integer nuDeaths;
	private Integer nuAssists;

	/*
	 * get and set
	 */

	public Integer getNuChampion() {
		return nuChampion;
	}
	public void setNuChampion(Integer nuChampion) {
		this.nuChampion = nuChampion;
	}
	public String getNmChampion() {
		return nmChampion;
	}
	public void setNmChampion(String nmChampion) {
		this.nmChampion = nmChampion;
	}
	public String getUrlPhoto() {
		return urlPhoto;
	}
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
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

}
