package com.example.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OneTimePassword {

	@Id
	private String mobile;
	private String otp;
	private Date time;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
