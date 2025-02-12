package com.personalAssist.MindMap.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalAssist.MindMap.Model.User;
import com.personalAssist.MindMap.dto.LoginRequestDTO;
import com.personalAssist.MindMap.dto.UserDTO;
import com.personalAssist.MindMap.repository.UserRepository;
import com.personalAssist.MindMap.util.JwtUtil;
import com.personalAssist.MindMap.util.PasswordEncoder;
import com.personalAssist.MindMap.util.UserWrapper;
import com.personalAssist.MindMap.wrapper.LoginApiResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserRepository userRepository;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		System.out.println("Login Request data @@@ " + loginRequestDTO.getEmail());
		User user = userRepository.findByEmail(loginRequestDTO.getEmail());

		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
		}

		if (!PasswordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credetials");
		}

		String token = JwtUtil.generateToken(user.getEmail());
		UserDTO userDTO = UserWrapper.toDTO(user);
		return ResponseEntity.ok(new LoginApiResponse(token, userDTO));
	}

}
