package com.personalAssist.MindMap.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.personalAssist.MindMap.Model.Role;
import com.personalAssist.MindMap.repository.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.count() == 0) {
			roleRepository.save(new Role(RoleType.USER));
			roleRepository.save(new Role(RoleType.ADMIN));
			roleRepository.save(new Role(RoleType.MODERATOR));
			roleRepository.save(new Role(RoleType.SERVICE_PROVIDER));
			roleRepository.save(new Role(RoleType.SERVICE_SEEKER));
			roleRepository.save(new Role(RoleType.DONOR));
			roleRepository.save(new Role(RoleType.DONATION_SEEKER));

			System.out.println("Default roles added to database.");
		}
	}

}
