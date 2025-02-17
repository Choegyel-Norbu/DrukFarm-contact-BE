package com.personalAssist.DrukFarm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.personalAssist.DrukFarm.Model.BuyerDetail;
import com.personalAssist.DrukFarm.Model.FarmerDetail;
import com.personalAssist.DrukFarm.Model.Product;
import com.personalAssist.DrukFarm.Model.TransporterDetail;
import com.personalAssist.DrukFarm.Model.User;
import com.personalAssist.DrukFarm.Model.UserServiceModal;
import com.personalAssist.DrukFarm.dto.BuyerDetailDTO;
import com.personalAssist.DrukFarm.dto.ServiceRequestDTO;
import com.personalAssist.DrukFarm.dto.UserDTO;
import com.personalAssist.DrukFarm.util.RoleType;

@Component
public interface UserService {

	public UserDTO addUser(UserDTO userDTO);
	public List<User> getAllUsers();
	public User getById(Long id);
	public String deleteUser(Long id);
	public Optional<User> updateUser(Long id, UserDTO updateUser);
	public UserDTO addRole(String email, List<String> roleNames);
	
	public User addUserServiceOffered(ServiceRequestDTO serviceRequestDTO);
	public List<String> fetchServicesForUser(ServiceRequestDTO serviceRequestDTO);
	
	public BuyerDetail addBuyerDetail(ServiceRequestDTO serviceRequestDTO);
	
	public TransporterDetail addTransporterDetail(ServiceRequestDTO serviceRequestDTO);
	
	public FarmerDetail addFarmerDetil(ServiceRequestDTO serviceRequestDTO);
	
	public String generateOTP(String recipient, String channel);
	public void sendEmailOTP(String email, String otp);
}



	