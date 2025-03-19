package com.personalAssist.DrukFarm.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.personalAssist.DrukFarm.Model.Role;

public class UserDTO {

	private Long id;
	private String userName;
	private String email;
	private String phone;
	private String password;
	private String location;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Set<String> requestRole;
	private Set<Role> responseRole;
	private List<String> userServices = new ArrayList<>();

	public UserDTO() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public UserDTO(Long id, String userName, String email, String phone, String password, List<String> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public UserDTO(Long id, String userName, String email, String phone, Set<Role> userRoles) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public List<String> getUserServices() {
		return userServices;
	}

	public void setUserServices(List<String> userServices) {
		this.userServices = userServices;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<String> getRequestRole() {
		return requestRole;
	}

	public void setRequestRole(Set<String> requestRole) {
		this.requestRole = requestRole;
	}

	public Set<Role> getResponseRole() {
		return responseRole;
	}

	public void setResponseRole(Set<Role> responseRole) {
		this.responseRole = responseRole;
	}
	
	

}
