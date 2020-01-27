package com.example.crons;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.dao.OtpRepository;

@Component
public class OtpCron {

	@Autowired
	private OtpRepository otpRep;
	
	@Scheduled(cron = "0 0/1 * * * *" )
	public void cronJobSch() {
		System.out.println("Cron executing!!");		
		otpRep.deleteInvalidOtpRecords();
	}
	
	public static void main(String[] args) {
		Date d = new Date();
		long t = d.getTime();
		System.out.println(t);
		long t2 = t-(10*60*1000);
		System.out.println(t2);
		
//		"delete from onetimepassword where time<t2";
	}
}
