package com.service;
 
import javax.annotation.Resource;
 
import org.springframework.stereotype.Service;
 
import com.mapper.UserMapper;
import com.pojo.User;
 
@Service("userService")
public class UserService {
	@Resource
	private UserMapper dao;
	/*
	* 通过id获取数据
	*/
	public User findById(String id)throws Exception{
		return (User)dao.selectByPrimaryKey(id);
	}
}
