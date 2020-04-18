//package com.shop.main.service;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Service
//public class CustomSignCheck {
//
//	public String gotoSignError(HttpServletRequest request, RedirectAttributes redirect) {
//		String reference = request.getHeader("Referer");
//		redirect.addFlashAttribute("sign_error", 1);
//		return "redirect:" + reference.substring(reference.indexOf("/main/") + 5);
//	}
//	
//}
