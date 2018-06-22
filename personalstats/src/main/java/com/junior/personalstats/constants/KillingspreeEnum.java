package com.junior.personalstats.constants;

public enum KillingspreeEnum {

	DOUBLE_KILL("DOUBLE_KILL"),
	TRIPLE_KILL("TRIPLE_KILL"),
	QUADRA_KILL("QUADRA_KILL"),
	PENTA_KILL("PENTA_KILL");

	private String type;

	KillingspreeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public static boolean isDoubleKill(String deType){
		return DOUBLE_KILL.getType().equals(deType);
	}

	public static boolean isTripleKill(String deType){
		return TRIPLE_KILL.getType().equals(deType);
	}

	public static boolean isQuadraKill(String deType){
		return QUADRA_KILL.getType().equals(deType);
	}

	public static boolean isPentaKill(String deType){
		return PENTA_KILL.getType().equals(deType);
	}
}
