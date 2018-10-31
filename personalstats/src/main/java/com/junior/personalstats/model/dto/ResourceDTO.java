package com.junior.personalstats.model.dto;

import java.util.Date;

public class ResourceDTO {

	private ChampionDTO champion;
	private Double nuDmgPerGold;
	private Date dtMatch;
	private boolean isWin;
	private Long nuDmgDealt;
	private Integer nuGameLength;
	private Integer nuGoldEarned;
	
	public ResourceDTO(ChampionDTO champion, Double nuDmgPerGold, Date dtMatch, boolean isWin, Long nuDmgDealt, Integer nuGameLength, Integer nuGoldEarned) {
		super();
		this.champion = champion;
		this.nuDmgPerGold = nuDmgPerGold;
		this.dtMatch = dtMatch;
		this.isWin = isWin;
		this.nuDmgDealt = nuDmgDealt;
		this.nuGameLength = nuGameLength;
		this.nuGoldEarned = nuGoldEarned;
	}
	
	public void reset() {
		this.champion = null;
		this.nuDmgPerGold = null; 
		this.dtMatch = null;
		this.isWin = false;
	}
	
	public Double getNuDmgPerGold() {
		return nuDmgPerGold;
	}
	public void setNuDmgPerGold(Double nuDmgPerGold) {
		this.nuDmgPerGold = nuDmgPerGold;
	}
	public Date getDtMatch() {
		return dtMatch;
	}
	public void setDtMatch(Date dtMatch) {
		this.dtMatch = dtMatch;
	}
	public boolean isWin() {
		return isWin;
	}
	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	public ChampionDTO getChampion() {
		return champion;
	}

	public void setChampion(ChampionDTO champion) {
		this.champion = champion;
	}

	public Long getNuDmgDealt() {
		return nuDmgDealt;
	}

	public void setNuDmgDealt(Long nuDmgDealt) {
		this.nuDmgDealt = nuDmgDealt;
	}

	public Integer getNuGameLength() {
		return nuGameLength;
	}

	public void setNuGameLength(Integer nuGameLength) {
		this.nuGameLength = nuGameLength;
	}

	public Integer getNuGoldEarned() {
		return nuGoldEarned;
	}

	public void setNuGoldEarned(Integer nuGoldEarned) {
		this.nuGoldEarned = nuGoldEarned;
	}
	
	
}
