package ssh.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ssh.entity.Loginusers;

public class UserDaoImpl implements UserDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Loginusers> getAllUser() {
		Session session = sessionFactory.openSession();
		List<Loginusers> List = session.createQuery("FROM Loginusers").list();
		for (Loginusers user : List) {
			System.out.println(user);
		}
		session.close();
		sessionFactory.close();
		return List;
	}

	@Override
	public void addUser(Loginusers user) {
		Session session = sessionFactory.openSession();
		session.save(user);
		System.out.println("用户添加成功！");
		session.close();
	}

	@Override
	public boolean verification(Loginusers user) {
		Session session = sessionFactory.openSession();
		// List<Loginusers> List = session.createQuery("FROM Loginusers WHERE userName="
		// + user.getUserName()
		// + " AND password=" + user.getPassword()).list();
		List<Loginusers> List = session.createQuery("FROM Loginusers WHERE userName=:name AND password=:password")
				.setString("name", user.getUserName()).setString("password", user.getPassword()).list();
		session.close();
		if (List.size() > 0) {
			System.out.println("用户查询成功！");
			return true;
		}
		System.out.println("用户查询失败！");
		return false;
	}

}
