package com.cvilla.medievalia.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.domain.User;

public class UserDAO implements IUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void nuevo(User u) {
		Session s = this.sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		s.persist(u);
		t.commit();
		s.close();

	}

	public List<User> list() {
		Session s = this.sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<User> l = s.createQuery("from users").list();
		s.close();
		return l;
	}

}
