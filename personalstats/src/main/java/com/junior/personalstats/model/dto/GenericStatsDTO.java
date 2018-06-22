package com.junior.personalstats.model.dto;

import java.io.Serializable;

public class GenericStatsDTO implements Serializable{

	private String nuGameId;
	private Integer nuValor;

	public GenericStatsDTO() {
		//hide
	}

	public GenericStatsDTO(String nuGameId, Integer nuValor) {
		super();
		this.nuGameId = nuGameId;
		this.nuValor = nuValor;
	}

	public String getNuGameId() {
		return nuGameId;
	}
	public void setNuGameId(String nuGameId) {
		this.nuGameId = nuGameId;
	}
	public Integer getNuValor() {
		return nuValor;
	}
	public void setNuValor(Integer nuValor) {
		this.nuValor = nuValor;
	}




}
