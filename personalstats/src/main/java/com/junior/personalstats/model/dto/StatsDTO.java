package com.junior.personalstats.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsDTO implements Serializable {

	private static final long serialVersionUID = 3769933642118715435L;

	@JsonProperty("kills")
	private Integer nuKills;
	
	@JsonProperty("deaths")
	private Integer nuDeaths;
	
	@JsonProperty("assists")
	private Integer nuAssists;
	
	@JsonProperty("longestTimeSpentLiving")
	private Integer nuMaxTimeAlive;
	
	@JsonProperty("doubleKills")
	private Integer nuDoubleKills;
	
	@JsonProperty("trippleKills")
	private Integer nuTripleKills;
	
	@JsonProperty("quadraKills")
	private Integer nuQuadraKills;
	
	@JsonProperty("pentaKills")
	private Integer nuPentaKills;
	
	@JsonProperty("totalDamageDealt")
	private Integer nuDmgDealt;
	
	@JsonProperty("visionScore")
	private Integer nuVisionScore;
	
	@JsonProperty("totalMinionsKilled")
	private Integer nuTotalMinions;

	@JsonProperty("goldEarned")
	private Integer nuGoldEarned;
	
	@JsonProperty("totalDamageDealtToChampions")
	private Long nuDamageDealt;
	
	/*
	 * GET AND SETS
	 */
	
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

	public Long getNuDamageDealt() {
		return nuDamageDealt;
	}

	public void setNuDamageDealt(Long nuDamageDealt) {
		this.nuDamageDealt = nuDamageDealt;
	}
	
	
}
