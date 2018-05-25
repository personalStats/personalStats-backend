package com.junior.personalstats.constants;

public enum TypeEnum {

	KILL("KILL"),
	DEATH("DEATH"),
	ASSIST("ASSIST");

	private String type;

	TypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



}
