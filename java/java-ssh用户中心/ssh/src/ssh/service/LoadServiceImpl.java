package ssh.service;

import ssh.dao.UserDao;
import ssh.entity.Loginusers;

public class LoadServiceImpl implements LoadService {
	private UserDao ud;

	public void setUD(UserDao ud) {
		this.ud = ud;
	}

	@Override
	public boolean verification(Loginusers user) {
		if (ud.verification(user)) {
			return true;
		}
		return false;
	}
}
