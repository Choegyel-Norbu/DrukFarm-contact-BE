package com.personalAssist.DrukFarm.dto;

import java.util.List;

import com.personalAssist.DrukFarm.Model.FarmerDetail;
import com.personalAssist.DrukFarm.Model.TransporterDetail;

public class DetailsRequestDTO {

	private String email;
	private List<String> services;
	private BuyerDetailDTO buyerDetailDTO;
	private TransporterDetailDTO transporterDetail;
	private FarmerDetailDTO farmerDetail;
	private ProduceDTO produce;

	public DetailsRequestDTO() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}

	public BuyerDetailDTO getBuyerDetailDTO() {
		return buyerDetailDTO;
	}

	public void setBuyerDetailDTO(BuyerDetailDTO buyerDetailDTO) {
		this.buyerDetailDTO = buyerDetailDTO;
	}

	public TransporterDetailDTO getTransporterDetail() {
		return transporterDetail;
	}

	public void setTransporterDetail(TransporterDetailDTO transporterDetail) {
		this.transporterDetail = transporterDetail;
	}

	public FarmerDetailDTO getFarmerDetail() {
		return farmerDetail;
	}

	public void setFarmerDetail(FarmerDetailDTO farmerDetail) {
		this.farmerDetail = farmerDetail;
	}

	public ProduceDTO getProduceDTO() {
		return produce;
	}

	public void setProduceDTO(ProduceDTO produceDTO) {
		this.produce = produceDTO;
	}

}
