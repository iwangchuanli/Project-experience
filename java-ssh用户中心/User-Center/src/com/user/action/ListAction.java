package com.user.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.user.entity.User;
import com.user.service.ListService;

public class ListAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private ListService ls = null;

	public void setLS(ListService ls) {
		this.ls = ls;
	}

	@Override
	public String execute() throws Exception {
		List<User> userList = ls.getAllUser();
		System.out.println("½á¹û¼¯£º" + userList.size());
		ActionContext ac = ActionContext.getContext();
		ac.put("userList", userList);
		return "success";
	}
}
