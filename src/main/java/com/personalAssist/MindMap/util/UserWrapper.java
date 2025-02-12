package com.personalAssist.MindMap.util;

import com.personalAssist.MindMap.Model.User;
import com.personalAssist.MindMap.dto.UserDTO;

public class UserWrapper {

	public static User toEntity(UserDTO userDTO) {

		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		return user;
	}

	public static UserDTO toDTO(User user) {
		return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getRoles());
	}

}
