package com.personalAssist.MindMap.dto;

import java.util.List;

public class ServiceRequestDTO {
	
	private String email;
	private List<String> services;
	
	public ServiceRequestDTO() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getServices() {
		return services;
	}
	public void setServices(List<String> services) {
		this.services = services;
	}
	
	

}
