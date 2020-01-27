package com.example.dao;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OtpRepositoryImpl implements OtpRepositoryCustom {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void deleteInvalidOtpRecords() {
		System.out.println("deleteInvalidOtpRecords :: OtpRepositoryImpl");
		SessionFactory sf = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = sf.openSession();
		Query q = session.createQuery("delete from OneTimePassword where time<:t");
		long t = System.currentTimeMillis()-(2*60*1000);
		q.setParameter("t", t);
		System.out.println("query preparation done!!");
		Transaction transaction = session.beginTransaction();
		q.executeUpdate();
		transaction.commit();
		session.close();
		System.out.println("Old records deleted!!");
	}
}
