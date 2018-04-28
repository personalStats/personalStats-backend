package com.junior.personalstats.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profile")
public class ProfileDTO {

	@Id
	private Integer cdProfile;
	private String deProfile;
	private String nuAccount;
	
	public ProfileDTO(Integer cdProfile, String deProfile, String nuAccount) {
		this.cdProfile = cdProfile;
		this.deProfile = deProfile;
		this.nuAccount = nuAccount;
	}
	
	/*
	 * GET AND SETS
	 */
	public Integer getCdProfile() {
		return cdProfile;
	}
	public void setCdProfile(Integer cdProfile) {
		this.cdProfile = cdProfile;
	}
	public String getDeProfile() {
		return deProfile;
	}
	public void setDeProfile(String deProfile) {
		this.deProfile = deProfile;
	}
	public String getNuAccount() {
		return nuAccount;
	}
	public void setNuAccount(String nuAccount) {
		this.nuAccount = nuAccount;
	}
}
