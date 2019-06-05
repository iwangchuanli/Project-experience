package ssh.action;

import com.opensymphony.xwork2.ActionSupport;

import ssh.entity.Loginusers;
import ssh.service.RegisterService;

public class RegisterAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int id;
	private String userName;
	private String password;
	private RegisterService rs = null;

	public void setRS(RegisterService rs) {
		this.rs = rs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		if (userName.length() > 3 && password.length() > 6) {
			Loginusers user = new Loginusers();
			user.setUserName(userName);
			user.setPassword(password);
			rs.addUser(user);
			return "success";
		} else {
			return "error";
		}

	}

}
