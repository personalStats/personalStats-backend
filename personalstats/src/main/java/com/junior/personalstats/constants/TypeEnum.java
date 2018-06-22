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

	public static boolean isKill(String type) {
		return TypeEnum.KILL.getType().equals(type);
	}

	public static boolean isDeath(String type) {
		return TypeEnum.DEATH.getType().equals(type);
	}

	public static boolean isAssist(String type) {
		return TypeEnum.ASSIST.getType().equals(type);
	}



}
