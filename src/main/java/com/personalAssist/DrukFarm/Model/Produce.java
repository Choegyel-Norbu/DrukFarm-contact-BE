package com.personalAssist.DrukFarm.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.personalAssist.DrukFarm.enums.ProductCategoryEnum;
import com.personalAssist.DrukFarm.util.RoleType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produce_table")
public class Produce {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	@Column(nullable = false)
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

	@ManyToOne
	@JoinColumn(name = "farmer_id", nullable = false)
	private FarmerDetail farmerDetail;

	@OneToMany(mappedBy = "produce", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AppImage> images;

	public Produce() {
		super();
	}

	public Produce(String name, FarmerDetail farmerDetail) {
		super();
		this.name = name;
		this.farmerDetail = farmerDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FarmerDetail getFarmerDetail() {
		return farmerDetail;
	}

	public void setFarmerDetail(FarmerDetail farmerDetail) {
		this.farmerDetail = farmerDetail;
	}

	public String getCategory() {
		return category;
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
