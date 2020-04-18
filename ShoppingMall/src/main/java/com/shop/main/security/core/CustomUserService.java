package com.shop.main.security.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.shop.admin.service.MemberServices;
import com.shop.main.security.role.CustomUser;

@Repository("userService")
public class CustomUserService implements UserDetailsService {

	@Autowired
	private MemberServices service;
	
	//아이디값으로 유저의 정보를 전부 가져온다.
	@Override
	public UserDetails loadUserByUsername(String u_account) throws UsernameNotFoundException {
		CustomUser user = service.getUserDetailByAccount(u_account);
		if (user == null) {
			throw new UsernameNotFoundException(u_account);
		}
		return user;
	}
}
