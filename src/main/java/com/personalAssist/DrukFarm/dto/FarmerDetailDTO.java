package com.personalAssist.DrukFarm.dto;

public class FarmerDetailDTO {
	private String farmName;
	private String farmSize;
	private String harvestSeason;
	private Boolean transportRequired;

	public FarmerDetailDTO() {
		super();
	}

	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	public String getFarmSize() {
		return farmSize;
	}

	public void setFarmSize(String farmSize) {
		this.farmSize = farmSize;
	}

	public String getHarvestSeason() {
		return harvestSeason;
	}

	public void setHarvestSeason(String harvestSeason) {
		this.harvestSeason = harvestSeason;
	}

	public Boolean getTransportRequired() {
		return transportRequired;
	}

	public void setTransportRequired(Boolean transportRequired) {
		this.transportRequired = transportRequired;
	}

}
