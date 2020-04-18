package com.shop.product.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shop.main.service.CustomFileDelete;
import com.shop.product.service.ProductService;

@WebListener
public class SessionListener implements HttpSessionListener {
	
	private ProductService pServices;
	
	public void sessionCreated(HttpSessionEvent se)  { 
		
	}
	
	public void sessionDestroyed(HttpSessionEvent se)  { 
		if (pServices == null) {
			pServices = getProdcutServices(se);
		}		
	    //세션이 종료될때 실행될 코드를 기술하는 부분
		HttpSession session = se.getSession();
		
		CustomFileDelete fileDel = new CustomFileDelete();
		fileDel.fileDeleter(session, pServices);
	}
	
	private ProductService getProdcutServices(HttpSessionEvent se) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(se.getSession().getServletContext());
		return (ProductService)context.getBean("productServices");
	}
}
