package com.personalAssist.MindMap.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.personalAssist.MindMap.Model.Product;
import com.personalAssist.MindMap.Model.User;
import com.personalAssist.MindMap.Model.UserServiceModal;
import com.personalAssist.MindMap.dto.ServiceRequestDTO;
import com.personalAssist.MindMap.dto.UserDTO;
import com.personalAssist.MindMap.repository.UserRepository;
import com.personalAssist.MindMap.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;

	@PostMapping("/registration")
	public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {

		System.out.println("@@@ ------ reached in controller " + userDTO.getRoles());
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
	public Optional<User> updateUser(@PathVariable Long id, @RequestBody User product) {
		return userService.updateUser(id, product);
	}

	@PutMapping("/addRoles")
	public ResponseEntity<?> addRoles(@RequestBody UserDTO userDTO) {
		try {
			UserDTO DTO = userService.addRole(userDTO.getEmail(), userDTO.getRoles());
			System.out.println("User roles @@@ - "+ userDTO.getRoles());
			return ResponseEntity.status(HttpStatus.CREATED).body("Roles added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());

		}
	}
	
	@PostMapping("/addServices")
	public ResponseEntity<?> addUserServiceOffered(@RequestBody ServiceRequestDTO serviceRequestDTO) {
		try {
			User user = userService.addUserServiceOffered(serviceRequestDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("Services added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());

		}
	}
	
	@PostMapping("/getServices")
	public ResponseEntity<List<String>> fetchServicesForUser(@RequestBody ServiceRequestDTO serviceRequestDTO){
		List<String> userServiceModal = userService.fetchServicesForUser(serviceRequestDTO);
		return ResponseEntity.ok(userServiceModal);
	}
	
	@DeleteMapping("/delete")
	public String deleteUser() {
		userRepo.deleteAll();
		return "Deleted all users";
	}
}
