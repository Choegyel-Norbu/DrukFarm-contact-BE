package com.personalAssist.DrukFarm.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProduceDTO {

	private Long id;
	private String name;
	private String category;
	private float pricePerUnit;
	private int quantityAvailable;
	private String qualityGrade; // (e.g., Organic, Premium, Standard)
	private LocalDate harvestDate;
	private String cultivationMethod; // (e.g., Organic, Hydroponic, Greenhouse)
	private String storageAndShelfLife; // (e.g., Store in a cool, dry place, lasts 3 months)
	private String animalSource;
	private String packagingType;
	private String ripeNessLevel;
	private boolean delivery;

	public ProduceDTO() {
		super();
	}

	public ProduceDTO(Long id, String name, float pricePerUnit, int quantityAvailable, String animalSource,String cultivationMethod, LocalDate harvestDate,
			 String storageAndShelfLife, String category) {
		super();
		this.id = id;
		this.name = name;
		this.pricePerUnit = pricePerUnit;
		this.quantityAvailable = quantityAvailable;
		this.harvestDate = harvestDate;
		this.cultivationMethod = cultivationMethod;
		this.storageAndShelfLife = storageAndShelfLife;
		this.animalSource = animalSource;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(float pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public String getQualityGrade() {
		return qualityGrade;
	}

	public void setQualityGrade(String qualityGrade) {
		this.qualityGrade = qualityGrade;
	}

	public LocalDate getHarvestDate() {
		return harvestDate;
	}

	public void setHarvestDate(LocalDate harvestDate) {
		this.harvestDate = harvestDate;
	}

	public String getCultivationMethod() {
		return cultivationMethod;
	}

	public void setCultivationMethod(String cultivationMethod) {
		this.cultivationMethod = cultivationMethod;
	}

	public String getStorageAndShelfLife() {
		return storageAndShelfLife;
	}

	public void setStorageAndShelfLife(String storageAndShelfLife) {
		this.storageAndShelfLife = storageAndShelfLife;
	}

	public String getAnimalSource() {
		return animalSource;
	}

	public void setAnimalSource(String animalSource) {
		this.animalSource = animalSource;
	}

	public String getPackagingType() {
		return packagingType;
	}

	public void setPackagingType(String packagingType) {
		this.packagingType = packagingType;
	}

	public String getRipeNessLevel() {
		return ripeNessLevel;
	}

	public void setRipeNessLevel(String ripeNessLevel) {
		this.ripeNessLevel = ripeNessLevel;
	}

	public boolean isDelivery() {
		return delivery;
	}

	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}

}
