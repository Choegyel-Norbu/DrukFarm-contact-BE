package com.personalAssist.DrukFarm.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "buyer_details")

public class BuyerDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private String businessName;
	private String preferredProduce;
	private String purchaseHistory;
	private String deliveryPreference;
	private String businessType;
	private String businessLocation;

	public BuyerDetail() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getBusinessLocation() {
		return businessLocation;
	}

	public void setBusinessLocation(String businessLocation) {
		this.businessLocation = businessLocation;
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

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getPreferredProduce() {
		return preferredProduce;
	}

	public void setPreferredProduce(String preferredProduce) {
		this.preferredProduce = preferredProduce;
	}

	public String getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(String purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	public String getDeliveryPreference() {
		return deliveryPreference;
	}

	public void setDeliveryPreference(String deliveryPreference) {
		this.deliveryPreference = deliveryPreference;
	}

}
