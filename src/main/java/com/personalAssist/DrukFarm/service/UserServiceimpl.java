package com.personalAssist.DrukFarm.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personalAssist.DrukFarm.Model.BuyerDetail;
import com.personalAssist.DrukFarm.Model.DP;
import com.personalAssist.DrukFarm.Model.FarmerDetail;
import com.personalAssist.DrukFarm.Model.OTP;
import com.personalAssist.DrukFarm.Model.Produce;
import com.personalAssist.DrukFarm.Model.Role;
import com.personalAssist.DrukFarm.Model.TransporterDetail;
import com.personalAssist.DrukFarm.Model.User;
import com.personalAssist.DrukFarm.Model.UserServiceModal;
import com.personalAssist.DrukFarm.dto.DetailsRequestDTO;
import com.personalAssist.DrukFarm.dto.UserDTO;
import com.personalAssist.DrukFarm.repository.BuyerDetailRepository;
import com.personalAssist.DrukFarm.repository.DpRepository;
import com.personalAssist.DrukFarm.repository.FarmerDetailRepository;
import com.personalAssist.DrukFarm.repository.OtpRepository;
import com.personalAssist.DrukFarm.repository.ProduceRepository;
import com.personalAssist.DrukFarm.repository.RoleRepository;
import com.personalAssist.DrukFarm.repository.TransporterDetailRepository;
import com.personalAssist.DrukFarm.repository.UserRepository;
import com.personalAssist.DrukFarm.repository.UserServiceRepository;
import com.personalAssist.DrukFarm.util.AppConstants;
import com.personalAssist.DrukFarm.util.PasswordEncoder;
import com.personalAssist.DrukFarm.util.RoleType;
import com.personalAssist.DrukFarm.wrapper.UserWrapper;

import jakarta.transaction.Transactional;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserServiceRepository userServiceRepository;

	@Autowired
	BuyerDetailRepository buyerDetailRepository;

	@Autowired
	TransporterDetailRepository transporterRepository;

	@Autowired
	ProduceRepository produceRepository;

	@Autowired
	FarmerDetailRepository farmerDetailRepository;

	@Autowired
	OtpRepository otpRepository;

	@Autowired
	DpRepository dpRepository;

//	@Autowired
//	JavaMailSender mailSender;

	@Override
	public UserDTO addUser(UserDTO userDTO) {
		User user = UserWrapper.toEntity(userDTO);
		user.setPassword(PasswordEncoder.encode(userDTO.getPassword()));

		if (user.getRoles() == null) {
			user.setRoles(new HashSet<>());
		}

		Role userRole = roleRepository.findByName(RoleType.USER)
				.orElseThrow(() -> new RuntimeException("Default USER role not found"));

		user.getRoles().add(userRole);

		return UserWrapper.toDTO(userRepository.save(user));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> updateUser(Long id, UserDTO updateUser) {
		return userRepository.findById(id).map((user) -> {
			user.setUserName(updateUser.getUserName());
			user.setEmail(updateUser.getEmail());
			user.setPhone(updateUser.getPhone());
			user.setLocation(updateUser.getLocation());
			user.setUpdatedAt(updateUser.getUpdatedAt());
			return userRepository.save(user);
		});
	}

	@Override
	public User getById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public String deleteUser(Long id) {
		// TODO Auto-generated method stub
		User checkProduct = userRepository.findById(id).orElse(null);
		if (checkProduct != null) {
			userRepository.deleteById(id);
			return "Product id " + id + " deleted";
		}
		return "The product id " + id + " doesnot exist";
	}

	@Override
	public UserDTO addRole(String email, List<String> roleNames) {
		User user = userRepository.findByEmail(email);

		Set<Role> userRoles = roleNames.stream().map(roleName -> {
			RoleType roleType;
			try {
				roleType = RoleType.valueOf(roleName.toUpperCase());
			} catch (IllegalArgumentException e) {
				throw new RuntimeException("Invalid role: " + roleName);
			}
			return roleRepository.findByName(roleType)
					.orElseThrow(() -> new RuntimeException("Role not found: " + roleType));
		}).collect(Collectors.toSet());

		user.setRoles(userRoles);
		return UserWrapper.toDTO(userRepository.save(user));
	}

	@Override
	public User addUserServiceOffered(DetailsRequestDTO serviceRequestDTO) {
		User user = userRepository.findByEmail(serviceRequestDTO.getEmail());

		for (String service : serviceRequestDTO.getServices()) {
			UserServiceModal userServiceModal = userServiceRepository.findByServiceName(service).orElseGet(() -> {
				UserServiceModal newUserService = new UserServiceModal();
				newUserService.setServiceName(service);
				return userServiceRepository.save(newUserService);
			});
			user.addService(userServiceModal);
		}

		return userRepository.save(user);
	}

	@Override
	public List<String> fetchServicesForUser(DetailsRequestDTO detailsRequestDTO) {
		User user = userRepository.findByEmail(detailsRequestDTO.getEmail());

		List<UserServiceModal> userServices = userServiceRepository.findServicesByUserId(user.getId());
		List<String> serviceNames = new ArrayList<>();

		for (UserServiceModal m : userServices) {
			serviceNames.add(m.getServiceName());
		}

		return serviceNames;
	}

	@Override
	public BuyerDetail addBuyerDetail(DetailsRequestDTO detailsRequestDTO) {
		User user = userRepository.findByEmail(detailsRequestDTO.getEmail());

		BuyerDetail buyerDetail = new BuyerDetail();
		buyerDetail.setUser(user);
		buyerDetail.setBusinessName(detailsRequestDTO.getBuyerDetailDTO().getBusinessName());
		buyerDetail.setDeliveryPreference(detailsRequestDTO.getBuyerDetailDTO().getDeliveryPreference());
		buyerDetail.setPreferredProduce(detailsRequestDTO.getBuyerDetailDTO().getPreferredProduce());
		buyerDetail.setPurchaseHistory(detailsRequestDTO.getBuyerDetailDTO().getPurchaseHistory());

		return buyerDetailRepository.save(buyerDetail);
	}

	@Override
	public TransporterDetail addTransporterDetail(DetailsRequestDTO detailsRequestDTO) {
		User user = userRepository.findByEmail(detailsRequestDTO.getEmail());

		TransporterDetail transporterDetail = new TransporterDetail();
		transporterDetail.setUser(user);
		transporterDetail.setVehicleCapacity(detailsRequestDTO.getTransporterDetail().getVehicleCapacity());
		transporterDetail.setVehicleType(detailsRequestDTO.getTransporterDetail().getVehicleType());

		return transporterRepository.save(transporterDetail);
	}

	@Override
	public FarmerDetail addFarmerDetil(DetailsRequestDTO detailsRequestDTO) {
		User user = userRepository.findByEmail(detailsRequestDTO.getEmail());

		FarmerDetail farmerDetail = new FarmerDetail();
		farmerDetail.setFarmName(detailsRequestDTO.getFarmerDetail().getFarmName());
		farmerDetail.setFarmSize(detailsRequestDTO.getFarmerDetail().getFarmSize());
		farmerDetail.setHarvestSeason(detailsRequestDTO.getFarmerDetail().getHarvestSeason());
		farmerDetail.setTransportRequired(detailsRequestDTO.getFarmerDetail().getTransportRequired());
		farmerDetail.setUser(user);

		farmerDetailRepository.save(farmerDetail);
		
		return farmerDetail;
	}

	@Override
	public String generateOTP(String recipient, String channel) {
		String otp = String.valueOf(new Random().nextInt(900000) + 100000);
		OTP otpEntity = otpRepository.findByRecipient(recipient);

		if (otpEntity == null) {
			otpEntity = new OTP();
		}
		otpEntity.setChannel(channel);
		otpEntity.setOtpCode(otp);
		otpEntity.setExpirationTime(LocalDateTime.now().plusMinutes(AppConstants.OTP_EXPIRY_MINUTES));
		otpEntity.setRecipient(recipient);

		otpRepository.save(otpEntity);
		return otp;
	}

	@Override
	public void sendEmailOTP(String email, String otp) {
		// TODO Auto-generated method stub
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(email);
//		message.setSubject("Your OTP code");
//		message.setText("Your OTP is "+ otp +". It is vallid till 5 minutes");
//		
//		mailSender.send(message);

	}

	@Override
	@Transactional
	public String uploadDP(String email, MultipartFile file) {
		User user = userRepository.findByEmail(email);
		String fileName = null;

		Path uploadPath = Paths.get(AppConstants.UPLOAD_DIR);
		if (!Files.exists(uploadPath)) {
			try {
				Files.createDirectories(uploadPath);

			} catch (IOException e) {
				return " Could not create directory";
			}
		} else {
			fileName = UUID.randomUUID().toString() + file.getOriginalFilename().replace(" ", "");
			Path filePath = uploadPath.resolve(fileName);

			try {
				DP dpEntity = new DP();
				dpEntity.setUser(user);
				dpEntity.setFilePath(filePath.toString());
				dpEntity.setFileName(fileName);

				dpRepository.save(dpEntity);
				Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

			} catch (IOException e) {
				e.printStackTrace();
				return "File upload failed";
			}
		}

		return fileName;
	}

	@Override
	public String getDP(String email) {
		User user = userRepository.findByEmail(email);

		return dpRepository.fetchFileName(user.getId());
	}

	@Transactional
	@Override
	public String updateDP(String email, MultipartFile file) {
		User user = userRepository.findByEmail(email);

		Path updatePath = Paths.get(AppConstants.UPLOAD_DIR);

		String fileName = UUID.randomUUID().toString() + file.getOriginalFilename().replace(" ", "");
		Path filePath = updatePath.resolve(fileName);

		try {
			dpRepository.updateDP(user.getId(), fileName, filePath.toString());
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			e.printStackTrace();
			return "File upload failed";
		}

		return getDP(email);
	}
}
