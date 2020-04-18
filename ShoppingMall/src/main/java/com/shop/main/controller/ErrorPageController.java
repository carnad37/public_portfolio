package com.shop.main.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/error")
@Controller
public class ErrorPageController {
	
	private final int AUTHENTICATION_ACCESS_DENIED = 1;
	
	//AccessDenied ó��
	@RequestMapping("/anonymous")
	public String recommandSignIn(HttpServletRequest request, RedirectAttributes redirect) {
		String reference = request.getHeader("Referer");
		//���� request�� ���� ����. �ּҸ� ���� ���� ������ ���.
		if (reference == null) {
			return "redirect:/error/405";
		} else {
			HttpSession session = request.getSession();
			String beforeReferer = (String)session.getAttribute("beforeReferer");
			
			//Too many Redirect ����.
			if (beforeReferer != null && beforeReferer.equals(reference)) {
				session.removeAttribute("beforeReferer");
				return "redirect:/";
			}
			
			session.setAttribute("beforeReferer", reference);
			redirect.addFlashAttribute("sign_error", AUTHENTICATION_ACCESS_DENIED);
			
			//Referer ��Ʈ�ѷ��� �����̷�Ʈ
			String requestPath = request.getRequestURI().toString().substring(1);
			String mainPath = requestPath.substring(0, requestPath.indexOf("/"));
			System.out.println("server path : " + reference);
//			return "redirect:" + reference.substring(reference.indexOf("/ShoppingMall/") + 5);
			return "redirect:" + reference.split(mainPath)[1];
		}		
	}
	
	@RequestMapping("/404")
	public String goto404Page(Model model, HttpServletRequest request) {
		model.addAttribute("errorTitle", "�������� ã�� ���� �����ϴ�!");
		model.addAttribute("errorDescribe", "�߸��� �ּ��̰ų� ���� �������� ������ �Դϴ�.");
		model.addAttribute("errorCode", ErrorCodeSetting(404));
		model.addAttribute("errorReferer", request.getHeader("Referer"));
		return "/_main/error-page.error-temp";	
	}

	@RequestMapping("/400")
	public String goto400Page(Model model, HttpServletRequest request) {
		model.addAttribute("errorTitle", "�������� ã�� ���� �����ϴ�!");
		model.addAttribute("errorDescribe", "�߸��� �ּ��̰ų� ���� �������� ������ �Դϴ�.");
		model.addAttribute("errorCode", ErrorCodeSetting(400));
		model.addAttribute("errorReferer", request.getHeader("Referer"));
		return "/_main/error-page.error-temp";
	}

	@RequestMapping("/405")
	public String goto405Page(Model model, HttpServletRequest request) {
		model.addAttribute("errorTitle", "������ ������ �����ϴ�!");
		model.addAttribute("errorDescribe", "CSRF��ū�� ����Ǿ��ų� �߸��� ��û�Դϴ�. <br>�������� ������ ���ּ���.");
		model.addAttribute("errorCode", ErrorCodeSetting(405));
		if (request.getHeader("Referer") == null) {
			model.addAttribute("errorReferer", "/ShoppingMall/");
		} else {
			model.addAttribute("errorReferer", request.getHeader("Referer"));
		}		
		return "/_main/error-page.error-temp";
	}

	private Map<String, Integer> ErrorCodeSetting(int codeNumber) {
		Map<String, Integer> codeMap = new HashMap<String, Integer>();
		
		int target = codeNumber;
		codeMap.put("third", target % 10);
		
		target = target/10;
		codeMap.put("second", target % 10);

		target = target/10;
		codeMap.put("first", target);
		return codeMap;
	}
	
}
