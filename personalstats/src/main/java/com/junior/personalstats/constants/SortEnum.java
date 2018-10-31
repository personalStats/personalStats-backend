package com.junior.personalstats.constants;

public enum SortEnum {

	ASC("A"),
	DESC("D");

	private String type;

	SortEnum(String type) {
		this.type = type;
	}
	
	public static boolean isSortAsc(String type) {
		return ASC.getType().equals(type);
	}
	
	public static boolean isSortDesc(String type) {
		return DESC.getType().equals(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
}
