package com.junior.personalstats.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class MatchPositionDTO implements Serializable{

	@JsonProperty("x")
	@Getter @Setter private Integer nuPositionX;
	
	@JsonProperty("y")
	@Getter @Setter private Integer nuPositionY;
	
	
}
