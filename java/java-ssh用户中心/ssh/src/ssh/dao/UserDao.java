package ssh.dao;

import java.util.List;

import ssh.entity.Loginusers;

public interface UserDao {

	public List<Loginusers> getAllUser();
	
	public void addUser(Loginusers user);
	
	public boolean verification(Loginusers user);

}
