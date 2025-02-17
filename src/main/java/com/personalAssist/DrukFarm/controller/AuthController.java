package com.personalAssist.DrukFarm.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personalAssist.DrukFarm.Model.OTP;
import com.personalAssist.DrukFarm.Model.User;
import com.personalAssist.DrukFarm.dto.LoginRequestDTO;
import com.personalAssist.DrukFarm.dto.UserDTO;
import com.personalAssist.DrukFarm.repository.OtpRepository;
import com.personalAssist.DrukFarm.repository.UserRepository;
import com.personalAssist.DrukFarm.service.TwilioService;
import com.personalAssist.DrukFarm.service.UserService;
import com.personalAssist.DrukFarm.util.JwtUtil;
import com.personalAssist.DrukFarm.util.PasswordEncoder;
import com.personalAssist.DrukFarm.util.UserWrapper;
import com.personalAssist.DrukFarm.wrapper.LoginApiResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

//	@Autowired
//	TwilioService twilioService;

	@Autowired
	OtpRepository otpRepository;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		System.out.println("Login Request data @@@ " + loginRequestDTO.getEmail());

		User user = userRepository.findByPhone(loginRequestDTO.getPhone());

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

	@PostMapping("/generateOTP")
	public Map<String, String> generate(@RequestParam String recipient, @RequestParam String channel) {
		String otp = userService.generateOTP(recipient, channel);

		if (channel.equalsIgnoreCase("emial")) {
			userService.sendEmailOTP(recipient, otp);
		} else {
			twilioService.sendOTP(recipient, otp);
		}

		Map<String, String> response = new HashMap<>();
		response.put("message", "OTP sent successfully!");
		return response;
	}

	@PostMapping("/verifyOTP")
	public Map<String, String> verify(@RequestParam String recipient, @RequestParam String otp) {
		OTP otpVerify = otpRepository.fetchOTP(recipient, otp);
		
		Map<String, String> response = new HashMap<>();
		if(otpVerify != null) {
		response.put("Message", "Success");
		return response;
		}
		response.put("Message", "Failed");
		return response;
	}

}
