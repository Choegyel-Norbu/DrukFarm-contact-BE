package com.personalAssist.DrukFarm.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="famer_table")
public class FarmerDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	private String farmName;
	private String farmSize;
	private String harvestSeason;
	private Boolean transportRequired;
	
    @OneToMany(mappedBy = "farmerDetail", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Produce> availableProduce;
	
	public FarmerDetail() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public List<Produce> getAvailableProduce() {
		return availableProduce;
	}

	public void setAvailableProduce(List<Produce> availableProduce) {
		this.availableProduce = availableProduce;
	}
	
	
}
