package com.junior.personalstats.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@Document(collection = "profile")
public class Profile {

	@Id
	private String cdProfile;
	@JsonProperty("name")
	private String deProfile;
	@JsonProperty("accountId")
	private String nuAccount;
	private Date dtUltimaAtualizacao;
	
	public Profile() {
		
	}
	
	public Profile(String cdProfile, String deProfile, String nuAccount, Date dtUltimaAtualizacao) {
		this.cdProfile = cdProfile;
		this.deProfile = deProfile;
		this.nuAccount = nuAccount;
		this.dtUltimaAtualizacao = dtUltimaAtualizacao;
	}
	
	/*
	 * GET AND SETS
	 */
	public String getCdProfile() {
		return cdProfile;
	}
	public void setCdProfile(String cdProfile) {
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

	public Date getDtUltimaAtualizacao() {
		return dtUltimaAtualizacao;
	}

	public void setDtUltimaAtualizacao(Date dtUltimaAtualizacao) {
		this.dtUltimaAtualizacao = dtUltimaAtualizacao;
	}
	
}
