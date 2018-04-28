package com.junior.personalstats.constants;

public enum RoleEnum {

	TOP("T"), 
	JUNGLER("J"), 
	MID("M"), 
	ADC("A"), 
	SUPPORT("S");
	
	private String cdRole;
	
	RoleEnum(String cdRole) {
		this.cdRole = cdRole;
	}

	public String getCdRole() {
		return cdRole;
	}

	public void setCdRole(String cdRole) {
		this.cdRole = cdRole;
	}
	
	public String getValueByKey(String cdRole) {
		return RoleEnum.valueOf(cdRole).getCdRole();
	}
	
}
