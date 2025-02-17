package com.personalAssist.DrukFarm.wrapper;

import com.personalAssist.DrukFarm.dto.UserDTO;

public class LoginApiResponse {

	private String token;
	private UserDTO userDTO;

	public LoginApiResponse() {
		super();
	}

	public LoginApiResponse(String token, UserDTO userDTO) {
		this.token = token;
		this.userDTO = userDTO;
	}

	public String getToken() {
		return token;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

}
