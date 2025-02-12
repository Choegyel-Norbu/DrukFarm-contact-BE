package com.personalAssist.MindMap.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.personalAssist.MindMap.Model.Role;

public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;
	private List<String> roles;
	private Set<Role> userRoles;
	private List<String> userServices = new ArrayList<>();

	public UserDTO() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public UserDTO(Long id, String firstName, String lastName, String email, String phone, String password,
			List<String> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.roles = roles;
	}

	public UserDTO(Long id, String firstName, String lastName, String email, String phone, Set<Role> userRoles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.roles = roles;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

}
