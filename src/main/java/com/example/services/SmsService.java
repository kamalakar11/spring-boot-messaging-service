package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.delegate.SmsDelegate;

@RestController
public class SmsService {

	@Autowired
	private SmsDelegate delegate;
	
	@RequestMapping(value="/sendSms/{mobile}/{text}", method = RequestMethod.GET)
	public String sendSms(@PathVariable("mobile")String mobile, @PathVariable("text") String text) {
		String result = delegate.sendSms(mobile, text);
		return result;
	}
}
