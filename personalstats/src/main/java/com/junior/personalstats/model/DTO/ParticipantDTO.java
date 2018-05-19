package com.junior.personalstats.model.DTO;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParticipantDTO implements Serializable{

	private static final long serialVersionUID = -6626808250943409341L;

	@JsonProperty("championId")
	private Integer nuChampion;
	
	@JsonProperty("teamId")
	private Integer nuTeam;
	
	@JsonProperty("stats")
	private StatsDTO stats;
	
	@JsonProperty("participantId")
	private Integer nuParticipant;

	/* 
	 * GET AND SETS
	 */
	public Integer getNuChampion() {
		return nuChampion;
	}

	public void setNuChampion(Integer nuChampion) {
		this.nuChampion = nuChampion;
	}

	public StatsDTO getStats() {
		return stats;
	}

	public void setStats(StatsDTO stats) {
		this.stats = stats;
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
	
	
}
