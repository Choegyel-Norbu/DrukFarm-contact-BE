package com.personalAssist.DrukFarm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.personalAssist.DrukFarm.Model.Role;
import com.personalAssist.DrukFarm.repository.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.count() == 0) {
			roleRepository.save(new Role(RoleType.USER));
			roleRepository.save(new Role(RoleType.ADMIN));
			roleRepository.save(new Role(RoleType.FARMER));
			roleRepository.save(new Role(RoleType.BUYER));
			roleRepository.save(new Role(RoleType.TRANSPORTER));

			System.out.println("Default roles added to database.");
		}
	}

}
