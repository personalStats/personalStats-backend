package com.junior.personalstats.constants;

public enum DragonEnum {

	OCEAN("WATER_DRAGON"),
	MOUNTAIN("MOUNTAIN_DRAGON"),
	FIRE("FIRE_DRAGON"),
	CLOUD("AIR_DRAGON");

	private String type;

	DragonEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public static boolean isFireDragon(String deType){
		return FIRE.getType().equals(deType);
	}

	public static boolean isCloudDragon(String deType){
		return CLOUD.getType().equals(deType);
	}

	public static boolean isMountainDragon(String deType){
		return MOUNTAIN.getType().equals(deType);
	}

	public static boolean isOceanDragon(String deType){
		return OCEAN.getType().equals(deType);
	}

}
