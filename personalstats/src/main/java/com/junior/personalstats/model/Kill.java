package com.junior.personalstats.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.junior.personalstats.model.dto.MatchPositionDTO;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Kill implements Serializable{

	@JsonProperty("killerId")
	@Getter @Setter private Integer nuChampionKill;

	@JsonProperty("victimId")
	@Getter @Setter private Integer nuChampionDeath;

	@JsonProperty("assistingParticipantIds")
	@Getter @Setter private List<Integer> nuChampionAssist;

	@JsonProperty("timestamp")
	@Getter @Setter private Timestamp dtKill;

	@JsonProperty("position")
	@Getter @Setter private MatchPositionDTO matchPositionDTO;

	private String type;

	private BigInteger cdMatch;

	private String cdProfile;

	public Integer getNuChampionKill() {
		return nuChampionKill;
	}

	public void setNuChampionKill(Integer nuChampionKill) {
		this.nuChampionKill = nuChampionKill;
	}

	public Integer getNuChampionDeath() {
		return nuChampionDeath;
	}

	public void setNuChampionDeath(Integer nuChampionDeath) {
		this.nuChampionDeath = nuChampionDeath;
	}

	public List<Integer> getNuChampionAssist() {
		return nuChampionAssist;
	}

	public void setNuChampionAssist(List<Integer> nuChampionAssist) {
		this.nuChampionAssist = nuChampionAssist;
	}

	public Timestamp getDtKill() {
		return dtKill;
	}

	public void setDtKill(Timestamp dtKill) {
		this.dtKill = dtKill;
	}

	public MatchPositionDTO getMatchPositionDTO() {
		return matchPositionDTO;
	}

	public void setMatchPositionDTO(MatchPositionDTO matchPositionDTO) {
		this.matchPositionDTO = matchPositionDTO;
	}

	public BigInteger getCdMatch() {
		return cdMatch;
	}

	public void setCdMatch(BigInteger cdMatch) {
		this.cdMatch = cdMatch;
	}

	public String getCdProfile() {
		return cdProfile;
	}

	public void setCdProfile(String cdProfile) {
		this.cdProfile = cdProfile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



}
