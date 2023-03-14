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
	* ͨ��id��ȡ����
	*/
	public User findById(String id)throws Exception{
		return (User)dao.selectByPrimaryKey(id);
	}
}
