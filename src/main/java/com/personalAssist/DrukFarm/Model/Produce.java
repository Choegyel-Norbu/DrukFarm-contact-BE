package com.personalAssist.DrukFarm.Model;

import com.personalAssist.DrukFarm.enums.ProductCategoryEnum;
import com.personalAssist.DrukFarm.util.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produce_table")
public class Produce {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "farmer_id", nullable = false)
	private FarmerDetail farmerDetail;
	
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
	

}
