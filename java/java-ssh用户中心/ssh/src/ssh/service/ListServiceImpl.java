package ssh.service;

import java.util.List;

import ssh.dao.UserDao;
import ssh.entity.Loginusers;

public class ListServiceImpl implements ListService{
	
	private UserDao ud;
	public void setUD(UserDao ud) {
		this.ud = ud;
	}
	@Override
	public List<Loginusers> getAllUser() {
		List<Loginusers>  userList= ud.getAllUser();
		return userList;
	}

}
