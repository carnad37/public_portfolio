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
	    //������ ����ɶ� ����� �ڵ带 ����ϴ� �κ�
		HttpSession session = se.getSession();
		
		CustomFileDelete fileDel = new CustomFileDelete();
		fileDel.fileDeleter(session, pServices);
	}
	
	private ProductService getProdcutServices(HttpSessionEvent se) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(se.getSession().getServletContext());
		return (ProductService)context.getBean("productServices");
	}
}
