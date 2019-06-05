package com.structs.action;

import com.opensymphony.xwork2.Action;

public class UserAction implements Action {
	private String userName;
	private String address;
	private String telephone;
	private String email;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String execute() throws Exception {
		if (userName.length() > 6) {
			return "success";
		} else {
			return "error";
		}
	}
}
