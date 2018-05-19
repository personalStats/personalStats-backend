package com.junior.personalstats.model.DTO;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchDTO implements Serializable {

	@JsonProperty("matches")
	private List<MatchReferenceDTO> matchReferences;
	
	private MatchDetailsDTO matchDetailsDTO;

	/*
	 * GET AND SETS
	 */
	public List<MatchReferenceDTO> getMatchReferences() {
		return matchReferences;
	}

	public void setMatchReferences(List<MatchReferenceDTO> matchReferences) {
		this.matchReferences = matchReferences;
	}

	public MatchDetailsDTO getMatchDetailsDTO() {
		return matchDetailsDTO;
	}

	public void setMatchDetailsDTO(MatchDetailsDTO matchDetailsDTO) {
		this.matchDetailsDTO = matchDetailsDTO;
	}
	
	
	
}
