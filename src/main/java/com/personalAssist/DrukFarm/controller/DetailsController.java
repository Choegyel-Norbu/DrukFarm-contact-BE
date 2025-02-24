package com.personalAssist.DrukFarm.controller;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalAssist.DrukFarm.dto.BuyerDetailDTO;
import com.personalAssist.DrukFarm.dto.FarmerDetailDTO;
import com.personalAssist.DrukFarm.dto.TransporterDetailDTO;
import com.personalAssist.DrukFarm.service.DetailsService;

@RestController
@RequestMapping("/api")
public class DetailsController {
	
	@Autowired
	DetailsService detailService;

	@PostMapping("/addFarmerDetials/{email}")
	public ResponseEntity<?> addFarmerDetails(@PathVariable String email,
			@RequestBody FarmerDetailDTO farmerDetailDTO) {
		String farmerDetail = detailService.addFarmerDetails(email, farmerDetailDTO);

		if (farmerDetail != null) {
			return ResponseEntity.ok(farmerDetail);
		} else {
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body("Error adding farmers detial");
		}
	}
	
	@PostMapping("/addBuyerDetials/{email}")
	public ResponseEntity<?> addBuyerDetails(@PathVariable String email,
			@RequestBody BuyerDetailDTO buyerDetailDTO) {
		String buyerDetail = detailService.addBuyerDetails(email, buyerDetailDTO);

		if (buyerDetail != null) {
			return ResponseEntity.ok(buyerDetail);
		} else {
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body("Error adding buyers details");
		}
	}
	
	@PostMapping("/addTransporterDetials/{email}")
	public ResponseEntity<?> addTransporterDetails(@PathVariable String email,
			@RequestBody TransporterDetailDTO transporterDetailDTO) {
		String transporterDetail = detailService.addTransporterDetails(email, transporterDetailDTO);

		if (transporterDetail != null) {
			return ResponseEntity.ok(transporterDetail);
		} else {
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body("Error adding transporter details");
		}
	}
	
}
