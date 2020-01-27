package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.delegate.OtpDelegate;

@RestController
public class OtpService {

	@Autowired
	private OtpDelegate del;
	
	@RequestMapping(value="/generateOtp/{mobile}", method=RequestMethod.GET)
	public String generateOtp(@PathVariable("mobile") String mobile) {
		System.out.println("generateOtp :: OtpService");
		String result = del.generateOtp(mobile);
		return result;
	}
	
	@RequestMapping(value="/validateOtp/{mobile}/{otp}", method=RequestMethod.GET)
	public String validateOtp(@PathVariable("mobile") String mobile, @PathVariable("otp") String otp) {
		System.out.println("validateOtp :: OtpService");
		String validateOtp = del.validateOtp(mobile, otp);
		return validateOtp;
	}
}
