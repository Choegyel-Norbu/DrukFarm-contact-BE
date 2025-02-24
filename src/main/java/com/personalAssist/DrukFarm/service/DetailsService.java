package com.personalAssist.DrukFarm.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.personalAssist.DrukFarm.Model.Produce;
import com.personalAssist.DrukFarm.dto.BuyerDetailDTO;
import com.personalAssist.DrukFarm.dto.FarmerDetailDTO;
import com.personalAssist.DrukFarm.dto.ProduceDTO;
import com.personalAssist.DrukFarm.dto.TransporterDetailDTO;

@Component
public interface DetailsService {
	
	public String addFarmerDetails(String email, FarmerDetailDTO farmerDetailDTO);
	public String updateFramerDetials(Long userId, FarmerDetailDTO farmerDetailDTO);
	
	public String addBuyerDetails(String email, BuyerDetailDTO farmerDetailDTO);
	public String updateBuyerDetials(Long userId, BuyerDetailDTO farmerDetailDTO);
	
	public String addTransporterDetails(String email, TransporterDetailDTO farmerDetailDTO);
	public String updateTransporterDetials(Long userId, TransporterDetailDTO farmerDetailDTO);

}
