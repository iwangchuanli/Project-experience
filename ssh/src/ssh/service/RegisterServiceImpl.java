package ssh.service;

import ssh.dao.UserDao;
import ssh.entity.Loginusers;

public class RegisterServiceImpl implements RegisterService {
	private UserDao ud;

	public void setUD(UserDao ud) {
		this.ud = ud;
	}

	@Override
	public void addUser(Loginusers user) {
		ud.addUser(user);
	}

}
