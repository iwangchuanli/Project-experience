package ssh.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import ssh.entity.Loginusers;
import ssh.service.ListService;

public class ListAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private ListService ls = null;

	public void setLS(ListService ls) {
		this.ls = ls;
	}

	@Override
	public String execute() throws Exception {
		List<Loginusers> userList = ls.getAllUser();
		System.out.println("�������" + userList.size());
		ActionContext ac = ActionContext.getContext();
		ac.put("userList", userList);
		return "success";
	}
}
