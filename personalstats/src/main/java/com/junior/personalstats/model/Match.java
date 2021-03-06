package com.junior.personalstats.model;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.junior.personalstats.model.dto.MatchDTO;
import com.junior.personalstats.model.dto.ParticipantDTO;
import com.junior.personalstats.model.dto.TeamDTO;

@Document(collection = "match")
public class Match {

	@Id
	private BigInteger cdMatch;

	private Profile profile;

	private Integer nuGameId;
	private Integer nuChampion;
	private Date dtMatch;

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
	private Long nuDmgDealt;
	private Integer nuVisionScore;
	private Integer nuTeam;
	private Integer nuParticipant;
	private Integer nuTotalMinions;
	private Integer nuGoldEarned;
	private Integer nuDragons;
	private Integer nuMaxCriticalStrike;
	private Integer nuMaxKillingSpree;
	private Long nuTimeCcGiven;
	private Integer nuGameLength;

	public Match getMatchFromMatchDTO(MatchDTO matchDTO) {
		this.nuGameId = matchDTO.getMatchDetailsDTO().getCdMatch();
		this.nuChampion = matchDTO.getMatchDetailsDTO().getNuChampion();
		this.dtMatch = matchDTO.getMatchDetailsDTO().getDtMatch();
		this.nuSeason = matchDTO.getMatchDetailsDTO().getNuSeason();
		this.nuGameLength = matchDTO.getMatchDetailsDTO().getNuGameLength();

		mountParticipantData(matchDTO);
		mountTeamData(matchDTO);

		return this;
	}


	private void mountParticipantData(MatchDTO matchDTO) {
		ParticipantDTO participantDTO = matchDTO.getMatchDetailsDTO().getParticipants().stream().filter(pa -> pa.getNuChampion().equals(nuChampion)).findFirst().get();
		this.nuKills = participantDTO.getStats().getNuKills();
		this.nuDeaths = participantDTO.getStats().getNuDeaths();
		this.nuAssists = participantDTO.getStats().getNuAssists();
		this.nuDoubleKills = participantDTO.getStats().getNuDoubleKills();
		this.nuTripleKills = participantDTO.getStats().getNuTripleKills();
		this.nuQuadraKills = participantDTO.getStats().getNuQuadraKills();
		this.nuPentaKills = participantDTO.getStats().getNuPentaKills();
		this.nuDmgDealt = participantDTO.getStats().getNuDamageDealt();
		this.nuVisionScore = participantDTO.getStats().getNuVisionScore();
		this.nuTotalMinions = participantDTO.getStats().getNuTotalMinions();
		this.nuTeam = participantDTO.getNuTeam();
		this.nuGoldEarned = participantDTO.getStats().getNuGoldEarned();
		this.nuParticipant = participantDTO.getNuParticipant();
		this.nuMaxCriticalStrike = participantDTO.getStats().getNuMaxCriticalStrike();
		this.nuMaxKillingSpree = participantDTO.getStats().getNuMaxKillingSpree();
		this.nuTimeCcGiven = participantDTO.getStats().getNuTimeCcGiven();
	}
	
	private void mountTeamData(MatchDTO matchDTO) {
		TeamDTO teamDTO = matchDTO.getMatchDetailsDTO().getTeams().stream().filter(t -> t.getIdTeam().equals(this.nuTeam)).findFirst().get();
		this.isFirstDragon = teamDTO.isFirstDragon();
		this.isFirstKill = teamDTO.isFirstKill();
		this.isFirstTower = teamDTO.isFirstTower();
		this.nuDragons = teamDTO.getNuDragonKills();
		this.isWin = "Win".equals(teamDTO.isWin());
	}

	/*
	 * GET AND SET
	 */
	public BigInteger  getCdMatch() {
		return cdMatch;
	}

	public void setCdMatch(BigInteger cdMatch) {
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

	public Long getNuDmgDealt() {
		return nuDmgDealt;
	}

	public void setNuDmgDealt(Long nuDmgDealt) {
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

	public Date getDtMatch() {
		return dtMatch;
	}

	public void setDtMatch(Date dtMatch) {
		this.dtMatch = dtMatch;
	}

	public Integer getNuTeam() {
		return nuTeam;
	}

	public void setNuTeam(Integer nuTeam) {
		this.nuTeam = nuTeam;
	}

	public Integer getNuParticipant() {
		return nuParticipant;
	}

	public void setNuParticipant(Integer nuParticipant) {
		this.nuParticipant = nuParticipant;
	}

	public Integer getNuTotalMinions() {
		return nuTotalMinions;
	}

	public void setNuTotalMinions(Integer nuTotalMinions) {
		this.nuTotalMinions = nuTotalMinions;
	}

	public Integer getNuGoldEarned() {
		return nuGoldEarned;
	}

	public void setNuGoldEarned(Integer nuGoldEarned) {
		this.nuGoldEarned = nuGoldEarned;
	}

	public Integer getNuDragons() {
		return nuDragons;
	}

	public void setNuDragons(Integer nuDragons) {
		this.nuDragons = nuDragons;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Integer getNuMaxCriticalStrike() {
		return nuMaxCriticalStrike;
	}

	public void setNuMaxCriticalStrike(Integer nuMaxCriticalStrike) {
		this.nuMaxCriticalStrike = nuMaxCriticalStrike;
	}

	public Integer getNuMaxKillingSpree() {
		return nuMaxKillingSpree;
	}

	public void setNuMaxKillingSpree(Integer nuMaxKillingSpree) {
		this.nuMaxKillingSpree = nuMaxKillingSpree;
	}

	public Long getNuTimeCcGiven() {
		return nuTimeCcGiven;
	}

	public void setNuTimeCcGiven(Long nuTimeCcGiven) {
		this.nuTimeCcGiven = nuTimeCcGiven;
	}

	public Integer getNuGameLength() {
		return nuGameLength;
	}

	public void setNuGameLength(Integer nuGameLength) {
		this.nuGameLength = nuGameLength;
	}


}
