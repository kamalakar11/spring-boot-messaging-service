package com.example.delegate;

import java.util.Date;
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
		o.setTime(new Date());
		dao.save(o);
		System.out.println("Saved to database!!");
		return result;
	}

}
