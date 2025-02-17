package com.personalAssist.DrukFarm.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.personalAssist.DrukFarm.Model.ProductCategory;
import com.personalAssist.DrukFarm.Model.Role;
import com.personalAssist.DrukFarm.enums.ProductCategoryEnum;
import com.personalAssist.DrukFarm.repository.ProductCategoryRepository;
import com.personalAssist.DrukFarm.util.RoleType;

public class CategoryInitializer implements CommandLineRunner{
	
	@Autowired
	ProductCategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {
		
		
	}

}
