package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY HH:mm:ss");

		Date date = new Date();

		String frmtdDate = dateFormat.format(date);
		
		System.out.println(date);
		System.out.println("frmtdDate: " + frmtdDate);
		System.out.println("time stamp : " + date.getTime());
		long t = date.getTime();
		
		Date d = new Date(t);
		System.out.println("time stamp to date : " + d);
		System.out.println(dateFormat.format(d));
	}
}
