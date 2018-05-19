package com.junior.personalstats.model.DTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "champion")
public class ChampionDTO {
	
	@Id
	private Integer cdChampion;
	private String deChampion;
	
	/* 
	 * GET AND SETS
	 */

	public Integer getCdChampion() {
		return cdChampion;
	}
	public void setCdChampion(Integer cdChampion) {
		this.cdChampion = cdChampion;
	}
	public String getDeChampion() {
		return deChampion;
	}
	public void setDeChampion(String deChampion) {
		this.deChampion = deChampion;
	}
	
	

}
