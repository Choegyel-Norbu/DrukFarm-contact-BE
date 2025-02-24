package com.personalAssist.DrukFarm.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
import com.personalAssist.DrukFarm.repository.UserRepository;
import com.personalAssist.DrukFarm.service.UserService;
import com.personalAssist.DrukFarm.util.AppConstants;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepo;

	@PostMapping("/registration")
	public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
		if (userRepo.findByEmail(userDTO.getEmail()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already taken");
		}
		if (userRepo.findByPhone(userDTO.getPhone()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Phone number is already taken");
		}

		UserDTO result = userService.addUser(userDTO);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
	}

	@GetMapping("/getUsers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/getUser/{id}")
	public User getById(@PathVariable Long id) {
		return userService.getById(id);
	}

	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}

	@PatchMapping("/updateUser/{id}")
	public Optional<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		return userService.updateUser(id, userDTO);
	}

	@PutMapping("/addRoles")
	public ResponseEntity<?> addRoles(@RequestBody UserDTO userDTO) {
		try {
			UserDTO DTO = userService.addRole(userDTO.getEmail(), userDTO.getRoles());
			return ResponseEntity.status(HttpStatus.CREATED).body("Roles added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());

		}
	}

	@PostMapping("/addServices")
	public ResponseEntity<?> addUserServiceOffered(@RequestBody DetailsRequestDTO serviceRequestDTO) {
		try {
			User user = userService.addUserServiceOffered(serviceRequestDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("Services added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());

		}
	}

	@PostMapping("/getServices")
	public ResponseEntity<List<String>> fetchServicesForUser(@RequestBody DetailsRequestDTO serviceRequestDTO) {
		List<String> userServiceModal = userService.fetchServicesForUser(serviceRequestDTO);
		return ResponseEntity.ok(userServiceModal);
	}

	@DeleteMapping("/delete")
	public String deleteUser() {
		userRepo.deleteAll();
		return "Deleted all users";
	}

	@PostMapping("/addBuyerDetail")
	public BuyerDetail addBuyerDetails(@RequestBody DetailsRequestDTO serviceRequestDTO) {
		return userService.addBuyerDetail(serviceRequestDTO);
	}

	@PostMapping("/addTransporterDetail")
	public TransporterDetail addTransporterDetail(@RequestBody DetailsRequestDTO serviceRequestDTO) {
		return userService.addTransporterDetail(serviceRequestDTO);
	}

	@PostMapping("/addFarmerDetail")
	public FarmerDetail addFarmerDetail(@RequestBody DetailsRequestDTO serviceRequestDTO) {
		return userService.addFarmerDetil(serviceRequestDTO);
	}

	@PostMapping("/uploadDP/{email}")
	public ResponseEntity<String> uploadDP(@PathVariable String email, @RequestParam("file") MultipartFile imageFile, HttpServletRequest request) {
		String fileName = userService.uploadDP(email, imageFile);
		
		try {
			Path filePath = Paths.get(AppConstants.UPLOAD_DIR + fileName).toAbsolutePath().normalize();
			String serverURL = request.getRequestURL().toString().replace(request.getRequestURI(), "");
			String imageURL = serverURL + "/uploads/"+ fileName;
			File file = filePath.toFile();
			if (file.exists() && file.isFile()) {
				return ResponseEntity.ok().body(imageURL);
			} else {
				return ResponseEntity.notFound().build();

			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}	}

	@GetMapping("/getDP/{email}")
	public ResponseEntity<String> getDP(@PathVariable String email, HttpServletRequest request) {

		String fileName = userService.getDP(email);
		try {
			Path filePath = Paths.get(AppConstants.UPLOAD_DIR + fileName).toAbsolutePath().normalize();
			String serverURL = request.getRequestURL().toString().replace(request.getRequestURI(), "");
			String imageURL = serverURL + "/uploads/" + fileName;

			File file = filePath.toFile();
			if (file.exists() && file.isFile()) {
				return ResponseEntity.ok().body(imageURL);
			} else {
				return ResponseEntity.notFound().build();

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PutMapping("/updateDP/{email}")
	public ResponseEntity<String> updateDP(@PathVariable String email, @RequestParam("file") MultipartFile imageFile,
			HttpServletRequest request) {
		
		String fileName = userService.updateDP(email, imageFile);
			
		try {
			Path filePath = Paths.get(AppConstants.UPLOAD_DIR + fileName).toAbsolutePath().normalize();
			String serverURL = request.getRequestURL().toString().replace(request.getRequestURI(), "");
			String imageURL = serverURL + "/uploads/"+ fileName;
			File file = filePath.toFile();
			if (file.exists() && file.isFile()) {
				return ResponseEntity.ok().body(imageURL);
			} else {
				return ResponseEntity.notFound().build();

			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}

}
