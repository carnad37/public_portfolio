package com.shop.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	//���� ȭ��
	@RequestMapping(value = "/")
	public String adminIndex() {
		return "/_admin/DashBoard.admin-temp";
	}	
	
}
