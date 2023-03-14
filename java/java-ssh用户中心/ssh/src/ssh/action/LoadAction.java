package ssh.action;

import com.opensymphony.xwork2.ActionSupport;

import ssh.entity.Loginusers;
import ssh.service.LoadService;

public class LoadAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private LoadService loadSer = null;

	public void setLoadSer(LoadService loadSer) {
		this.loadSer = loadSer;
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
		Loginusers user = new Loginusers();
		user.setUserName(userName);
		user.setPassword(password);
		if (loadSer.verification(user)) {
			return "success";
		}
		return "error";
	}
}
