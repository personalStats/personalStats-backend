package com.junior.personalstats.model.DTO;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeamDTO implements Serializable{

	private static final long serialVersionUID = 2663629505568017414L;

	@JsonProperty("teamId")
	private Integer idTeam;
	
	@JsonProperty("win")
	private String isWin;
	
	@JsonProperty("firstBlood")
	private boolean isFirstKill;
	
	@JsonProperty("firstTower")
	private boolean isFirstTower;
	
	@JsonProperty("isFirstDragon")
	private boolean isFirstDragon;

	/*
	 * GET AND SET
	 */
	
	public Integer getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}

	public String isWin() {
		return isWin;
	}

	public void setWin(String isWin) {
		this.isWin = isWin;
	}

	public boolean isFirstKill() {
		return isFirstKill;
	}

	public void setFirstKill(boolean isFirstKill) {
		this.isFirstKill = isFirstKill;
	}

	public boolean isFirstTower() {
		return isFirstTower;
	}

	public void setFirstTower(boolean isFirstTower) {
		this.isFirstTower = isFirstTower;
	}

	public boolean isFirstDragon() {
		return isFirstDragon;
	}

	public void setFirstDragon(boolean isFirstDragon) {
		this.isFirstDragon = isFirstDragon;
	}
	
	
}
