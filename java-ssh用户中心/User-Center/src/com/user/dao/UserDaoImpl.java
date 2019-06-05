package com.user.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.user.entity.User;

public class UserDaoImpl implements UserDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<User> getAllUser() {
		Session session = sessionFactory.openSession();
		List<User> List = session.createQuery("FROM User").list();
		for (User user : List) {
			System.out.println(user);
		}
		session.close();
		sessionFactory.close();
		return List;
	}

}
