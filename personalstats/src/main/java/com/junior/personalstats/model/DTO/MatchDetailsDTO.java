package com.junior.personalstats.model.DTO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.junior.personalstats.model.Profile;

public class MatchDetailsDTO {

	@JsonProperty("gameId")
	private Integer cdMatch;
	
	private String cdRole;
	private Date dtMatch;

	@JsonProperty("seasonId")
	private Integer nuSeason;
	private String cdQueue;
	
	@JsonProperty("win")
	private boolean isWin;
	
	private Integer nuChampion;
	
	private Profile profile;
	
	@Transient
	@JsonProperty("teams")
	private List<TeamDTO> teams;
	
	@Transient
	@JsonProperty("participants")
	private List<ParticipantDTO> participants;

	
	/*
	 * GETS AND SETS
	 * 
	 */
	
	public Integer getCdMatch() {
		return cdMatch;
	}

	public void setCdMatch(Integer cdMatch) {
		this.cdMatch = cdMatch;
	}

	public String getCdRole() {
		return cdRole;
	}

	public void setCdRole(String cdRole) {
		this.cdRole = cdRole;
	}

	public Date getDtMatch() {
		return dtMatch;
	}

	public void setDtMatch(Date dtMatch) {
		this.dtMatch = dtMatch;
	}

	public Integer getNuSeason() {
		return nuSeason;
	}

	public void setNuSeason(Integer nuSeason) {
		this.nuSeason = nuSeason;
	}

	public String getCdQueue() {
		return cdQueue;
	}

	public void setCdQueue(String cdQueue) {
		this.cdQueue = cdQueue;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<TeamDTO> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamDTO> teams) {
		this.teams = teams;
	}

	public List<ParticipantDTO> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ParticipantDTO> participants) {
		this.participants = participants;
	}

	public Integer getNuChampion() {
		return nuChampion;
	}

	public void setNuChampion(Integer nuChampion) {
		this.nuChampion = nuChampion;
	}

}
