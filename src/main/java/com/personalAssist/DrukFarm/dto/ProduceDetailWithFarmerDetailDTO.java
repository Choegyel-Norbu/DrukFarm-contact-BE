package com.personalAssist.DrukFarm.dto;

import java.time.LocalDate;

public class ProduceDetailWithFarmerDetailDTO {

	private String farmName;
	private String farmSize;
	private String harvestSeason;
	private String farmerName;
	private int quantity;
	private boolean delivery;
	private LocalDate harvestDate;

	public ProduceDetailWithFarmerDetailDTO() {
		super();
	}

	public ProduceDetailWithFarmerDetailDTO(String farmName, String farmSize, String farmerName,
			int quantity, boolean delivery, LocalDate harvestDate) {
		super();
		this.farmName = farmName;
		this.farmSize = farmSize;
		this.farmerName = farmerName;
		this.quantity = quantity;
		this.delivery = delivery;
		this.harvestDate = harvestDate;
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

	public String getFarmerName() {
		return farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isDelivery() {
		return delivery;
	}

	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}

	public LocalDate getHarvestDate() {
		return harvestDate;
	}

	public void setHarvestDate(LocalDate harvestDate) {
		this.harvestDate = harvestDate;
	}

}
