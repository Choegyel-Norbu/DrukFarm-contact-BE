package com.personalAssist.DrukFarm.dto;

public class BuyerDetailDTO {

	private String businessName;
	private String preferredProduce;
	private String businessType;
	private String businessLocation;
	private String purchaseHistory;
	private String deliveryPreference;

	public BuyerDetailDTO() {
		super();
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

}
