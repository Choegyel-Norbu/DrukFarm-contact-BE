package com.personalAssist.DrukFarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.OTP;

@Repository
public interface OtpRepository extends JpaRepository<OTP, Long>{
	
	OTP findByRecipient(String recipient);
	
	@Query("SELECT o FROM OTP o WHERE o.recipient = :recipient AND o.otpCode = :otp")
	OTP fetchOTP(String recipient, String otp);


}
