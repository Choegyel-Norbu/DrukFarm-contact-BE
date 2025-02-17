package com.personalAssist.DrukFarm.dto;

public class BuyerDetailDTO {

	private String businessName;
	private String preferredProduce;
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
	
	
}
