package com.junior.personalstats.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dragon implements Serializable{

	private String cdProfile;
	private Integer nuGameId;
	private String deTypeDragon;
	private String nuTimeDragon;

	/* GET AND SET */

	public String getCdProfile() {
		return cdProfile;
	}
	public void setCdProfile(String cdProfile) {
		this.cdProfile = cdProfile;
	}
	public Integer getNuGameId() {
		return nuGameId;
	}
	public void setNuGameId(Integer nuGameId) {
		this.nuGameId = nuGameId;
	}
	public String getNuTimeDragon() {
		return nuTimeDragon;
	}
	public void setNuTimeDragon(String nuTimeDragon) {
		this.nuTimeDragon = nuTimeDragon;
	}
	public String getDeTypeDragon() {
		return deTypeDragon;
	}
	public void setDeTypeDragon(String deTypeDragon) {
		this.deTypeDragon = deTypeDragon;
	}


}
