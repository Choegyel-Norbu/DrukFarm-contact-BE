package com.personalAssist.DrukFarm.util;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter implements Filter {

	private static final String[] AUTH_WHITELIST = { "/auth/login", // Login endpoint
			"/api/registration", "/api/addServices", "/uploads/", "/images/" // Registration endpoint
	};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String path = httpRequest.getRequestURI();

		if (isPathInWhitelist(path)) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}

		String authorizationHeader = httpRequest.getHeader("Authorization");

		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or Invalid token");
			return;
		}

		try {
			if (!JwtUtil.validateToken(authorizationHeader.substring(7))) {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
				return;
			}
		} catch (Exception e) {
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token validation failed");
			return;
		}

		chain.doFilter(httpRequest, httpResponse);
	}

	private boolean isPathInWhitelist(String path) {
		for (String whitelistedPath : AUTH_WHITELIST) {
			if (path.startsWith(whitelistedPath)) {
				return true;
			}
		}
		return false;
	}

}
