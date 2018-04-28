package com.junior.personalstats.model;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "match")
public class Match {

	@Id
	private Long cdMatch;
	
	private Integer nuGameId;
	private Integer nuChampion;
	private Timestamp dtMatch;
	
	private Integer nuSeason;
	private boolean isWin;
	private boolean isFirstKill;
	private boolean isFirstTower;
	private boolean isFirstDragon;
	private Integer nuKills;
	private Integer nuDeaths;
	private Integer nuAssists;
	private Integer nuMaxTimeAlive;
	private Integer nuDoubleKills;
	private Integer nuTripleKills;
	private Integer nuQuadraKills;
	private Integer nuPentaKills;
	private Integer nuDmgDealt;
	private Integer nuVisionScore;
	private Integer nuTeam;
	
	public Match getMatchFromMatchDTO(MatchDTO matchDTO) {
		this.nuGameId = matchDTO.getMatchDetailsDTO().getCdMatch();
		this.nuChampion = matchDTO.getMatchDetailsDTO().getNuChampion();
		this.dtMatch = matchDTO.getMatchDetailsDTO().getDtMatch();
		this.nuSeason = matchDTO.getMatchDetailsDTO().getNuSeason();
		this.isWin = matchDTO.getMatchDetailsDTO().isWin();
		for (ParticipantDTO participantDTO : matchDTO.getMatchDetailsDTO().getParticipants()) {
			if(participantDTO.getNuChampion() == nuChampion) {
				this.nuKills = participantDTO.getStats().getNuKills();
				this.nuDeaths = participantDTO.getStats().getNuDeaths();
				this.nuAssists = participantDTO.getStats().getNuAssists();
				this.nuDoubleKills = participantDTO.getStats().getNuDoubleKills();
				this.nuTripleKills = participantDTO.getStats().getNuTripleKills();
				this.nuQuadraKills = participantDTO.getStats().getNuQuadraKills();
				this.nuPentaKills = participantDTO.getStats().getNuPentaKills();
				this.nuDmgDealt = participantDTO.getStats().getNuDmgDealt();
				this.nuVisionScore = participantDTO.getStats().getNuVisionScore();
				this.nuTeam = participantDTO.getNuTeam();
			}
		}
		for (TeamDTO teamDTO : matchDTO.getMatchDetailsDTO().getTeams()) {
			if(teamDTO.getIdTeam() == this.nuTeam) {
				this.isFirstDragon = teamDTO.isFirstDragon();
				this.isFirstKill = teamDTO.isFirstKill();
				this.isFirstTower = teamDTO.isFirstTower();
			}
		}
		
		return this;
		
	}
	
	/*
	 * GET AND SET
	 */
	public Long getCdMatch() {
		return cdMatch;
	}

	public void setCdMatch(Long cdMatch) {
		this.cdMatch = cdMatch;
	}

	public Integer getNuSeason() {
		return nuSeason;
	}

	public void setNuSeason(Integer nuSeason) {
		this.nuSeason = nuSeason;
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

	public Integer getNuMaxTimeAlive() {
		return nuMaxTimeAlive;
	}

	public void setNuMaxTimeAlive(Integer nuMaxTimeAlive) {
		this.nuMaxTimeAlive = nuMaxTimeAlive;
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

	public Integer getNuDmgDealt() {
		return nuDmgDealt;
	}

	public void setNuDmgDealt(Integer nuDmgDealt) {
		this.nuDmgDealt = nuDmgDealt;
	}

	public Integer getNuVisionScore() {
		return nuVisionScore;
	}

	public void setNuVisionScore(Integer nuVisionScore) {
		this.nuVisionScore = nuVisionScore;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	public Integer getNuGameId() {
		return nuGameId;
	}

	public void setNuGameId(Integer nuGameId) {
		this.nuGameId = nuGameId;
	}

	public Integer getNuChampion() {
		return nuChampion;
	}

	public void setNuChampion(Integer nuChampion) {
		this.nuChampion = nuChampion;
	}

	public Timestamp getDtMatch() {
		return dtMatch;
	}

	public void setDtMatch(Timestamp dtMatch) {
		this.dtMatch = dtMatch;
	}

	public Integer getNuTeam() {
		return nuTeam;
	}

	public void setNuTeam(Integer nuTeam) {
		this.nuTeam = nuTeam;
	}

}
