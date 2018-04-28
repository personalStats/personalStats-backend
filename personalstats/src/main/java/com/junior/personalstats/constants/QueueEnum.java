package com.junior.personalstats.constants;

public enum QueueEnum {

	SOLOQ("S"),
	FLEX("F");
	
	private String cdQueue;
	
	QueueEnum(String cdQueue){
		this.cdQueue = cdQueue;
	}

	public String getCdQueue() {
		return cdQueue;
	}

	public void setCdQueue(String cdQueue) {
		this.cdQueue = cdQueue;
	}
	
}
