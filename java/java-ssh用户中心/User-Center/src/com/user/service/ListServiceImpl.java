package com.user.service;

import java.util.List;

import com.user.dao.UserDao;
import com.user.entity.User;

public class ListServiceImpl implements ListService{
	private UserDao ud;
	public void setUD(UserDao ud) {
		this.ud = ud;
	}
	@Override
	public List<User> getAllUser() {
		List<User>  userList= ud.getAllUser();
		return userList;
	}

}
