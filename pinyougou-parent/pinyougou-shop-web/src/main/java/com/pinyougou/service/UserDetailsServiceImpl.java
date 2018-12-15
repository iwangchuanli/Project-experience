package com.pinyougou.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;

public class UserDetailsServiceImpl implements UserDetailsService {

	private SellerService sellerService;
	
	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<GrantedAuthority> grantAuths = new ArrayList();
		grantAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		/**
		 * User:
		 * * 参数：
		 * 	* 1.用户名
		 *  * 2.密码
		 *  * 3.认证信息（角色）
		 */
		// 去数据库进行查询:
		TbSeller seller = sellerService.findOne(username);
		if(seller != null){
			if(seller.getStatus().equals("1")){
				return new User(username,seller.getPassword(),grantAuths );
			}else{
				return null;
			}
		}
		return null;
		
	}

}
