package com.junior.personalstats.model.dto;

import java.util.Date;

public class GeneralDataDTO {

	private Integer nuGameId;
	private ChampionDTO champion;
	private Integer nuCriticalStrike;
	private Date dtMatch;
	private boolean isWin;
	private Long nuDmgDealt;
	private Integer nuGameLength;
	private Integer nuPosition;
	private String nmProfile;
	
	public GeneralDataDTO(Integer nuGameId, ChampionDTO champion, Integer nuCriticalStrike, Date dtMatch, boolean isWin, Long nuDmgDealt, Integer nuGameLenth) {
		super();
		this.nuGameId = nuGameId;
		this.champion = champion;
		this.nuCriticalStrike = nuCriticalStrike;
		this.dtMatch = dtMatch;
		this.isWin = isWin;
		this.nuDmgDealt = nuDmgDealt;
		this.nuGameLength = nuGameLenth;
	}
	
	public GeneralDataDTO(String nmProfile, Long nuDmgDealt, Integer nuPosition) {
		this.nmProfile = nmProfile;
		this.nuDmgDealt = nuDmgDealt;
		this.nuPosition = nuPosition;
	}
	
	public void reset() {
		this.champion = null;
		this.nuCriticalStrike = null; 
		this.dtMatch = null;
		this.isWin = false;
	}

	public ChampionDTO getChampion() {
		return champion;
	}

	public void setChampion(ChampionDTO champion) {
		this.champion = champion;
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

	public Integer getNuCriticalStrike() {
		return nuCriticalStrike;
	}

	public void setNuCriticalStrike(Integer nuCriticalStrike) {
		this.nuCriticalStrike = nuCriticalStrike;
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

	public Integer getNuGameId() {
		return nuGameId;
	}

	public void setNuGameId(Integer nuGameId) {
		this.nuGameId = nuGameId;
	}

	public Integer getNuPosition() {
		return nuPosition;
	}

	public void setNuPosition(Integer nuPosition) {
		this.nuPosition = nuPosition;
	}

	public String getNmProfile() {
		return nmProfile;
	}

	public void setNmProfile(String nmProfile) {
		this.nmProfile = nmProfile;
	}
	
	
	
}
