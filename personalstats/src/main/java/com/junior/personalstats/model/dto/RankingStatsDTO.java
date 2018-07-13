package com.junior.personalstats.model.dto;

import java.io.Serializable;

public class RankingStatsDTO implements Serializable{

	private Integer nuRanking;
	private String nmProfile;
	private Integer nuValor;

	public RankingStatsDTO() {
		//hide
	}

	public RankingStatsDTO(Integer nuRanking, String nmProfile, Integer nuValor) {
		super();
		this.nuRanking = nuRanking;
		this.nmProfile = nmProfile;
		this.nuValor = nuValor;
	}

	public Integer getNuRanking() {
		return nuRanking;
	}

	public void setNuRanking(Integer nuRanking) {
		this.nuRanking = nuRanking;
	}

	public String getNmProfile() {
		return nmProfile;
	}

	public void setNmProfile(String nmProfile) {
		this.nmProfile = nmProfile;
	}

	public Integer getNuValor() {
		return nuValor;
	}

	public void setNuValor(Integer nuValor) {
		this.nuValor = nuValor;
	}






}
