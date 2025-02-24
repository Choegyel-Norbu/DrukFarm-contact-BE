package com.personalAssist.DrukFarm.wrapper;

import com.personalAssist.DrukFarm.Model.AppImage;
import com.personalAssist.DrukFarm.Model.Produce;
import com.personalAssist.DrukFarm.Model.User;
import com.personalAssist.DrukFarm.dto.AppImageDTO;
import com.personalAssist.DrukFarm.dto.ProduceDTO;
import com.personalAssist.DrukFarm.dto.UserDTO;

public class UserWrapper {

	public static User toEntity(UserDTO userDTO) {

		User user = new User();
		user.setUserName(userDTO.getUserName());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setLocation(userDTO.getLocation());
		return user;
	}

	public static UserDTO toDTO(User user) {
		return new UserDTO(user.getId(), user.getUserName(), user.getEmail(), user.getPhone(), user.getRoles());
	}

	public static ProduceDTO toProduceDTO(Produce produce) {
		return new ProduceDTO(produce.getId(), produce.getName(), produce.getPricePerUnit(), produce.getQuantityAvailable(), produce.getAnimalSource(),
				produce.getCultivationMethod(), produce.getHarvestDate(), produce.getStorageAndShelfLife(), produce.getCategory());
	}
	
	public static AppImageDTO toAppImageDTO(AppImage appImage) {
		return new AppImageDTO(appImage.getId());
	}

}
