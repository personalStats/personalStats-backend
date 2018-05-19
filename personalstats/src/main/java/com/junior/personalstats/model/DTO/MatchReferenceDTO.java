package com.junior.personalstats.model.DTO;

import java.io.Serializable;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchReferenceDTO implements Serializable{

	@JsonProperty("gameId")
	private int cdMatch;
	
	@JsonProperty("champion")
	private int nuChampion;
	
	@JsonProperty("timestamp")
	private Timestamp dtMatch;
	
	@JsonProperty("lane")
	private String deLane;

	/*
	 * GET AND SET
	 */
	
	public int getCdMatch() {
		return cdMatch;
	}

	public void setCdMatch(int cdMatch) {
		this.cdMatch = cdMatch;
	}

	public int getNuChampion() {
		return nuChampion;
	}

	public void setNuChampion(int nuChampion) {
		this.nuChampion = nuChampion;
	}

	public Timestamp getDtMatch() {
		return dtMatch;
	}

	public void setDtMatch(Timestamp dtMatch) {
		this.dtMatch = dtMatch;
	}

	public String getDeLane() {
		return deLane;
	}

	public void setDeLane(String deLane) {
		this.deLane = deLane;
	}
	
	
	
}
