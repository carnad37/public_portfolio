package com.shop.main.security.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.shop.admin.service.AdminServices;
import com.shop.admin.service.MemberServices;
import com.shop.main.security.role.CustomUser;

@Service("customSucessHandler")
public class CustomLoginSucessHandler implements AuthenticationSuccessHandler {

	@Autowired
	MemberServices services;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
//		�α��� ������, ���� �������� �ҷ���(���� UserDetailService�� ��ü)
//		CustomUser user = (CustomUser)authentication.getPrincipal();
//		user.setDetails(services.getUserDetailByAccount(user.getUsername()));
		
		response.sendRedirect("/ShoppingMall/");
	}

}
