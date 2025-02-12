package com.personalAssist.MindMap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.personalAssist.MindMap.Model.Product;
import com.personalAssist.MindMap.Model.User;
import com.personalAssist.MindMap.Model.UserServiceModal;
import com.personalAssist.MindMap.dto.ServiceRequestDTO;
import com.personalAssist.MindMap.dto.UserDTO;
import com.personalAssist.MindMap.util.RoleType;

@Component
public interface UserService {

	public UserDTO addUser(UserDTO userDTO);
	public List<User> getAllUsers();
	public User getById(Long id);
	public String deleteUser(Long id);
	public Optional<User> updateUser(Long id, User updateUser);
	public UserDTO addRole(String email, List<String> roleNames);
	
	public User addUserServiceOffered(ServiceRequestDTO serviceRequestDTO);
	public List<String> fetchServicesForUser(ServiceRequestDTO serviceRequestDTO);
}
	