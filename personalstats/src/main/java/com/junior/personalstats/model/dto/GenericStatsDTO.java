package com.junior.personalstats.model.dto;

import java.io.Serializable;

public class GenericStatsDTO implements Serializable{

	private Integer nuGameId;
	private Integer nuValor;
	private boolean isWin;

	public GenericStatsDTO() {
		//hide
	}

	public GenericStatsDTO(Integer nuGameId, Integer nuValor) {
		super();
		this.nuGameId = nuGameId;
		this.nuValor = nuValor == null ? 0 : null;
	}

	public GenericStatsDTO(Integer nuGameId, Integer nuValor, boolean isWin) {
		super();
		this.nuGameId = nuGameId;
		this.nuValor = nuValor == null ? 0 : nuValor;
		this.isWin = isWin;
	}

	public Integer getNuGameId() {
		return nuGameId;
	}
	public void setNuGameId(Integer nuGameId) {
		this.nuGameId = nuGameId;
	}
	public Integer getNuValor() {
		return nuValor;
	}
	public void setNuValor(Integer nuValor) {
		this.nuValor = nuValor;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}





}
