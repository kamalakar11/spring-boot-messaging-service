package com.example.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.pojo.Account;
import com.example.pojo.Msg;
import com.example.pojo.SmsRequest;
import com.example.pojo.TextMessage;
import com.google.gson.Gson;

@Service
public class SmsDelegate {

	public String sendSms(String mobile, String text) {
		StringBuilder url = new StringBuilder("https://www.smsgatewayhub.com/api/mt/SendSMS");
		SmsRequest smsReq = prepareRequest(mobile, text);

		Gson gson = new Gson();
		String smsReqJson = gson.toJson(smsReq);
		System.out.println("Reques in json format : " + smsReqJson);
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(smsReqJson, headers);
		ResponseEntity<String> result = rt.exchange(url.toString(), HttpMethod.POST, entity, String.class);

		String responseBody = result.getBody();
		System.out.println("Response from smsgatewayhub : " + result.getBody());
		TextMessage textMessage = gson.fromJson(responseBody, TextMessage.class);
		System.out.println("Message status : " + textMessage.getErrorMessage());
		return textMessage.getErrorMessage();
	}
	
	public SmsRequest prepareRequest(String mobile, String text) {
		SmsRequest smsReq = new SmsRequest();
		
		Account acc = new Account();
		acc.setUser("kamalakar11");
		acc.setPassword("862559");
		acc.setChannel("1");
		acc.setDCS("0");
		acc.setSenderId("SMSTST");
		
		List<Msg> msgList = new ArrayList<Msg>();
		Msg msg = new Msg();
		msg.setNumber(mobile);
		msg.setText(text);
		msgList.add(msg);
		
		smsReq.setAccount(acc);
		smsReq.setMessages(msgList);
		
		return smsReq;
	}
}
