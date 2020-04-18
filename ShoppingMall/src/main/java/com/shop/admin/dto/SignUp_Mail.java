package com.shop.admin.dto;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;




public class SignUp_Mail {

	@Autowired private ServletContext servletContext;
	StringBuilder mailText;	

	public SignUp_Mail() {
		System.out.println(servletContext.getRealPath("/"));
	}
	
	
	
}
