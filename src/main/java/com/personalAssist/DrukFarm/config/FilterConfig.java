package com.personalAssist.DrukFarm.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.personalAssist.DrukFarm.util.JwtFilter;


@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		FilterRegistrationBean<JwtFilter> registrationBean= new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/*"); // Apply to all URLs
        return registrationBean;
	}
}
