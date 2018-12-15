package com.pinyougou.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运营商登录的控制层的类
 * @author jt
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/showName")
	public Map showName(){
		Map map = new HashMap();
		// 获得用户名信息:
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		map.put("username", username);
		
		return map;
	}
	
}
