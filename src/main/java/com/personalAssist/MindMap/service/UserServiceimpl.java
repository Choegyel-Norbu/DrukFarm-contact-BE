package com.personalAssist.MindMap.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.personalAssist.MindMap.Model.Product;
import com.personalAssist.MindMap.Model.Role;
import com.personalAssist.MindMap.Model.User;
import com.personalAssist.MindMap.Model.UserServiceModal;
import com.personalAssist.MindMap.dto.ServiceRequestDTO;
import com.personalAssist.MindMap.dto.UserDTO;
import com.personalAssist.MindMap.repository.ProductRepository;
import com.personalAssist.MindMap.repository.RoleRepository;
import com.personalAssist.MindMap.repository.UserRepository;
import com.personalAssist.MindMap.repository.UserServiceRepository;
import com.personalAssist.MindMap.util.PasswordEncoder;
import com.personalAssist.MindMap.util.RoleType;
import com.personalAssist.MindMap.util.UserWrapper;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserServiceRepository userServiceRepository;

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
	public Optional<User> updateUser(Long id, User updateUser) {
		return userRepository.findById(id).map((user) -> {
			user.setFirstName(updateUser.getFirstName());
			user.setLastName(updateUser.getLastName());
			user.setPassword(updateUser.getPassword());
			user.setEmail(updateUser.getEmail());
			user.setPhone(updateUser.getPhone());
			user.setCreatedAt(updateUser.getCreatedAt());
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
	public User addUserServiceOffered(ServiceRequestDTO serviceRequestDTO) {
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
	public List<String> fetchServicesForUser(ServiceRequestDTO serviceRequestDTO) {
		User user = userRepository.findByEmail(serviceRequestDTO.getEmail());

		List<UserServiceModal> userServices = userServiceRepository.findServicesByUserId(user.getId());
		List<String> serviceNames = new ArrayList<>();

		for (UserServiceModal m : userServices) {
			serviceNames.add(m.getServiceName());
		}

		return serviceNames;
	}
}
