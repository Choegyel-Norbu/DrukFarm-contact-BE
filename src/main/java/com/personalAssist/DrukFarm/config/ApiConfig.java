package com.personalAssist.DrukFarm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiConfig implements WebMvcConfigurer {

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:8081", "http://192.168.235.149:8081")
				.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**")
				.addResourceLocations("file:/Users/mac/Documents/Projects/DrukFarm/DrukFarm-contact-BE/uploads/")
				.setCachePeriod(3600);

		registry.addResourceHandler("/images/**")
				.addResourceLocations("file:/Users/mac/Documents/Projects/DrukFarm/DrukFarm-contact-BE/images/")
				.setCachePeriod(3600);
	}
}
