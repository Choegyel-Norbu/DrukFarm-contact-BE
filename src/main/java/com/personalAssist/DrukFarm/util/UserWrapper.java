package com.personalAssist.DrukFarm.util;

import com.personalAssist.DrukFarm.Model.User;
import com.personalAssist.DrukFarm.dto.UserDTO;

public class UserWrapper {

	public static User toEntity(UserDTO userDTO) {

		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setLocation(userDTO.getLocation());
		return user;
	}

	public static UserDTO toDTO(User user) {
		return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getRoles());
	}

}
