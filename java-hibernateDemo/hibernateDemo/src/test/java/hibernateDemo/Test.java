package hibernateDemo;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.domain.User;

public class Test {

	public static void main(String[] args) {
		Configuration configure = new Configuration().configure();
		SessionFactory sessionFactory = configure.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		User user = new User();
		// user.setId("1");
		user.setName("hiberna");
		user.setDate(new Date());
		user.setAddress("hz");
		// hbmAdd(session,user,tx);
		hbmList(session, tx);

		session.close();
		sessionFactory.close();

	}

	/**
	 * 添加pojo
	 * 
	 * @param session
	 * @param user
	 * @param tx
	 */
	public static void hbmAdd(Session session, User user, Transaction tx) {
		session.save(user);
		tx.commit();
	}

	/**
	 * 内容更新
	 * 
	 * @param session
	 * @param user
	 * @param tx
	 */
	public static void hbmUpdate(Session session, User user, Transaction tx) {
		session.update(user);
		tx.commit();
	}

	/**
	 * 列表查询表格内容
	 * 
	 * @param session
	 * @param tx
	 */
	public static void hbmList(Session session, Transaction tx) {
		List users = session.createQuery("FROM User").list();
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			System.out.println(user);
		}
		tx.commit();
	}

	/**
	 * 根据姓名进行删除
	 * 
	 * @param session
	 * @param tx
	 * @param UserName
	 */
	public static void hbmDelete(Session session, Transaction tx, String UserName) {
		User user = (User) session.get(User.class, UserName);
		session.delete(user);
		tx.commit();
	}

}
