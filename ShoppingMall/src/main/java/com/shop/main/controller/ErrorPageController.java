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
	
	//AccessDenied 처리
	@RequestMapping("/anonymous")
	public String recommandSignIn(HttpServletRequest request, RedirectAttributes redirect) {
		String reference = request.getHeader("Referer");
		//이전 request가 없는 접근. 주소를 통한 강제 접근의 경우.
		if (reference == null) {
			return "redirect:/error/405";
		} else {
			HttpSession session = request.getSession();
			String beforeReferer = (String)session.getAttribute("beforeReferer");
			
			//Too many Redirect 방지.
			if (beforeReferer != null && beforeReferer.equals(reference)) {
				session.removeAttribute("beforeReferer");
				return "redirect:/";
			}
			
			session.setAttribute("beforeReferer", reference);
			redirect.addFlashAttribute("sign_error", AUTHENTICATION_ACCESS_DENIED);
			
			//Referer 컨트롤러로 리다이렉트
			String requestPath = request.getRequestURI().toString().substring(1);
			String mainPath = requestPath.substring(0, requestPath.indexOf("/"));
			System.out.println("server path : " + reference);
//			return "redirect:" + reference.substring(reference.indexOf("/ShoppingMall/") + 5);
			return "redirect:" + reference.split(mainPath)[1];
		}		
	}
	
	@RequestMapping("/404")
	public String goto404Page(Model model, HttpServletRequest request) {
		model.addAttribute("errorTitle", "페이지를 찾을 수가 없습니다!");
		model.addAttribute("errorDescribe", "잘못된 주소이거나 아직 제작중인 페이지 입니다.");
		model.addAttribute("errorCode", ErrorCodeSetting(404));
		model.addAttribute("errorReferer", request.getHeader("Referer"));
		return "/_main/error-page.error-temp";	
	}

	@RequestMapping("/400")
	public String goto400Page(Model model, HttpServletRequest request) {
		model.addAttribute("errorTitle", "페이지를 찾을 수가 없습니다!");
		model.addAttribute("errorDescribe", "잘못된 주소이거나 아직 제작중인 페이지 입니다.");
		model.addAttribute("errorCode", ErrorCodeSetting(400));
		model.addAttribute("errorReferer", request.getHeader("Referer"));
		return "/_main/error-page.error-temp";
	}

	@RequestMapping("/405")
	public String goto405Page(Model model, HttpServletRequest request) {
		model.addAttribute("errorTitle", "접근할 권한이 없습니다!");
		model.addAttribute("errorDescribe", "CSRF토큰이 만료되었거나 잘못된 요청입니다. <br>페이지에 재접속 해주세요.");
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
