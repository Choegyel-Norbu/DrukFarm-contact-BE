package com.personalAssist.DrukFarm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.personalAssist.DrukFarm.Model.BuyerDetail;
import com.personalAssist.DrukFarm.Model.FarmerDetail;
import com.personalAssist.DrukFarm.Model.Product;
import com.personalAssist.DrukFarm.Model.TransporterDetail;
import com.personalAssist.DrukFarm.Model.User;
import com.personalAssist.DrukFarm.Model.UserServiceModal;
import com.personalAssist.DrukFarm.dto.BuyerDetailDTO;
import com.personalAssist.DrukFarm.dto.DetailsRequestDTO;
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
	
	public User addUserServiceOffered(DetailsRequestDTO serviceRequestDTO);
	public List<String> fetchServicesForUser(DetailsRequestDTO serviceRequestDTO);
	
	public BuyerDetail addBuyerDetail(DetailsRequestDTO serviceRequestDTO);
	
	public TransporterDetail addTransporterDetail(DetailsRequestDTO serviceRequestDTO);
	
	public FarmerDetail addFarmerDetil(DetailsRequestDTO serviceRequestDTO);
	
	public String generateOTP(String recipient, String channel);
	public void sendEmailOTP(String email, String otp);
	
	public String uploadDP(String email, MultipartFile file);
	public String updateDP(String email, MultipartFile file);
	public String getDP(String email);
}



	