package com.personalAssist.DrukFarm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalAssist.DrukFarm.Model.BuyerDetail;
import com.personalAssist.DrukFarm.Model.FarmerDetail;
import com.personalAssist.DrukFarm.Model.TransporterDetail;
import com.personalAssist.DrukFarm.Model.User;
import com.personalAssist.DrukFarm.dto.BuyerDetailDTO;
import com.personalAssist.DrukFarm.dto.FarmerDetailDTO;
import com.personalAssist.DrukFarm.dto.TransporterDetailDTO;
import com.personalAssist.DrukFarm.repository.BuyerDetailRepository;
import com.personalAssist.DrukFarm.repository.FarmerDetailRepository;
import com.personalAssist.DrukFarm.repository.TransporterDetailRepository;
import com.personalAssist.DrukFarm.repository.UserRepository;

@Service
public class DetailsServiceImpl implements DetailsService{
	
	@Autowired
	FarmerDetailRepository  farmerDetailRepository;
	
	@Autowired
	BuyerDetailRepository buyerDetailRepository;
	
	@Autowired
	TransporterDetailRepository transporterDetailRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public String addFarmerDetails(String email, FarmerDetailDTO farmerDetailDTO) {
		User user = userRepository.findByEmail(email);
		FarmerDetail farmerDetail = new FarmerDetail();
		
		try {
			farmerDetail.setFarmName(farmerDetailDTO.getFarmName());
			farmerDetail.setFarmLocation(farmerDetailDTO.getFarmName());
			farmerDetail.setFarmSize(farmerDetailDTO.getFarmSize());
			farmerDetail.setUser(user);
			
			farmerDetailRepository.save(farmerDetail);
		}catch(Exception e) {
			return "Failed";

		}
		
		return "Success";
	}

	@Override
	public String updateFramerDetials(Long userId, FarmerDetailDTO farmerDetailDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addBuyerDetails(String email, BuyerDetailDTO farmerDetailDTO) {
		User user = userRepository.findByEmail(email);
		
		try {
			BuyerDetail buyerDetail = new BuyerDetail();
			buyerDetail.setBusinessName(farmerDetailDTO.getBusinessName());
			buyerDetail.setDeliveryPreference(farmerDetailDTO.getDeliveryPreference());
			buyerDetail.setPreferredProduce(farmerDetailDTO.getPreferredProduce());
			buyerDetail.setPurchaseHistory(farmerDetailDTO.getPurchaseHistory());
			buyerDetail.setUser(user);
			
			buyerDetailRepository.save(buyerDetail);
		}catch(Exception e) {
			return "Failed";
		}
		
		return "Success";
	}

	@Override
	public String updateBuyerDetials(Long userId, BuyerDetailDTO farmerDetailDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addTransporterDetails(String email, TransporterDetailDTO trasporterDetailDTO) {
		User user = userRepository.findByEmail(email);
		
		try {
			TransporterDetail trasporterDetail = new TransporterDetail();
			trasporterDetail.setUser(user);
			trasporterDetail.setVehicleCapacity(trasporterDetailDTO.getVehicleCapacity());
			trasporterDetail.setVehicleType(trasporterDetail.getVehicleType());
			
			transporterDetailRepository.save(trasporterDetail);
		}catch(Exception e) {
			return "failed";
		}
		
		return "success";
	}

	@Override
	public String updateTransporterDetials(Long userId, TransporterDetailDTO farmerDetailDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
