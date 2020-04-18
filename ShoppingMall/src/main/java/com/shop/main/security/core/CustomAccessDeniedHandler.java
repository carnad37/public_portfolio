package com.shop.main.security.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

@Service("accessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	//���� ��Ʈ�ѷ��� �������Ų��.
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		request.getRequestDispatcher("/error/anonymous").forward(request, response);
	}

}
