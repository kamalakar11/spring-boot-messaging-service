package com.example.delegate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.OtpRepository;
import com.example.pojo.OneTimePassword;

@Service
public class OtpDelegate {

	@Autowired
	private SmsDelegate smsDelegate;

	@Autowired
	private OtpRepository dao;
	
	public String generateOtp(String mobile) {
		Supplier<String> s = () -> {
			String otp = "";
			for (int i = 0; i < 6; i++) {
				otp = otp + (int) (Math.random() * 10);
			}
			return otp;
		};
		String otp = s.get();
		System.out.println(otp);
		String message = "Your OTP is : " + otp + " Please don't share it with anyone else!!";
		String result = smsDelegate.sendSms(mobile, message);
		OneTimePassword o = new OneTimePassword();
		o.setMobile(mobile);
		o.setOtp(otp);
		Date d = new Date();
		o.setTime(d.getTime());
		dao.save(o);
		System.out.println("Saved to database!!");
		return result;
	}

	public String validateOtp(String mobile, String otp) {
		System.out.println("validateOtp :: OtpDelegate");
		Optional<OneTimePassword> otpObject = dao.findById(mobile);
		OneTimePassword objectFromDb = otpObject.orElseGet(()->new OneTimePassword());
		String otpFromDb = objectFromDb.getOtp();
		if(otpFromDb != null && otp.equals(otpFromDb)) {
			return "true";
		} else {
			return "false";
		}
		
	}

}
