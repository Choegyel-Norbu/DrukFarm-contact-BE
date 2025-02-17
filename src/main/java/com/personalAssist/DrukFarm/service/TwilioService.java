package com.personalAssist.DrukFarm.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioService {
	
//	 // Twilio credentials
//    private static final String ACCOUNT_SID = "your_account_sid";
//    private static final String AUTH_TOKEN = "your_auth_token";
//    private static final String FROM_PHONE = "your_twilio_phone_number";
//
//    public void sendOTP(String phoneNumber, String otp) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message.creator(
//                new PhoneNumber(phoneNumber),
//                new PhoneNumber(FROM_PHONE),
//                "Your OTP is: " + otp + ". It is valid for 5 minutes.")
//            .create();
//    }
}
