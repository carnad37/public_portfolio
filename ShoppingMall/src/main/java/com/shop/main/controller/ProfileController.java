package com.shop.main.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.admin.dto.SignUp_Data;
import com.shop.admin.dto.UserData;
import com.shop.admin.service.MemberServices;
import com.shop.main.security.role.CustomUser;
import com.shop.product.dto.Product;
import com.shop.product.service.ProductService;

/**
 * Handles requests for the application home page.
 */
@RequestMapping(value="/profile")
@Controller
public class ProfileController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	private ProductService pServices;
	
	@Autowired
	private MemberServices mServices;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/view")
	public String userProfile() {
		return "/_member/profile-main.admin-temp";
	}
	
	@RequestMapping(value = "/sell")
	public String productSell() {
		return "/_member/profile-sell.admin-temp";
	}
	
	@RequestMapping(value = "/buy")
	public String productBuy() {
		return "/_member/profile-buy.admin-temp";
	}
	
	@RequestMapping(value = "/buy_data")
	@ResponseBody
	public Map<String, Object> productBuyTable(int page,
								   			   @AuthenticationPrincipal CustomUser user) {
		//현재 구매중 물품들을 불러옴.
		int userId = user.getDetails().getU_id();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Product> productList = pServices.selectOrderByU_id(userId, page * 10);
		if (productList == null) {
			return resultMap;
		} else if (productList.size() < 10) {
			int loopNum = 10 - productList.size();
			for (int i = 0; i < loopNum; i++) {
				Product product = new Product();
				product.setO_state(-1);
				productList.add(product);
			}
		}
		resultMap.put("productList", productList);
		
		if (page % 10 == 0) {
			resultMap.put("totalRows", pServices.countOrderByU_id(userId));
			return resultMap;
		}
		
		return resultMap;
	}
	
	
	@RequestMapping(value = "/leave")
	public void userLeave(@AuthenticationPrincipal CustomUser user,
						  HttpServletRequest request,
						  HttpServletResponse response) throws IOException {	
		//DB에서 유저의 enable상태를 N으로 전환한다.
		mServices.disableUser(user.getDetails().getU_id());
		
		//context에 유저 데이터가 남아있으면 로그아웃 시킨다.
		if (user != null) {
			new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
		}
	}
	
	@RequestMapping(value = "/sell_data")
	@ResponseBody
	public Map<String, Object> productSellTable(int page,
								   @AuthenticationPrincipal CustomUser user) {
		//현재 판매중인 물품들을 불러옴.
		int userId = user.getDetails().getU_id();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Product> productList = pServices.selectProductByU_id(userId, page * 10);
		if (productList == null) {
			//판매중인 물품이 없을 경우
			return resultMap;
		} else if (productList.size() < 10) {
			int loopNum = 10 - productList.size();
			for (int i = 0; i < loopNum; i++) {
				Product product = new Product();
				product.setO_state(-1);
				productList.add(product);
			}
		}
		resultMap.put("productList", productList);
		
		if (page % 10 == 0) {
			resultMap.put("totalRows", pServices.countProductByU_id(userId));
			return resultMap;
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String profileEdit(Model model,
			HttpServletRequest request,
			RedirectAttributes redirect ,
			@AuthenticationPrincipal CustomUser user) {

		model.addAttribute("userData", user.getDetails());
		return "/_member/profile-edit.admin-temp";
	}
	
	@RequestMapping(value = "/edit_process")
	public String profileEditProcess(SignUp_Data updateUserData,  @AuthenticationPrincipal CustomUser user) {
		//업데이트 데이터를 비교한다.
		//u_nick, u_pw, u_pw_confirm, u_confirmPw, u_name, u_phone, u_zipcode, u_addr, u_addr_detail
		UserData currentUserData = user.getDetails();
		
		UserData resUserData = new UserData();
		
 		if (!updateUserData.getU_name().equals(currentUserData.getU_name())) {
 			resUserData.setU_name(updateUserData.getU_name());
		}
		if (!updateUserData.getU_zipcode().equals(currentUserData.getU_zipcode())) {
			resUserData.setU_zipcode(updateUserData.getU_zipcode());
		}
		if (!updateUserData.getU_phone().equals(currentUserData.getU_phone())) {
			resUserData.setU_phone(updateUserData.getU_phone());
		}
		if (!updateUserData.getU_addr().equals(currentUserData.getU_addr())) {
			resUserData.setU_addr(updateUserData.getU_addr());
		}
		if (!updateUserData.getU_addr_detail().equals(currentUserData.getU_addr_detail())) {
			resUserData.setU_addr_detail(updateUserData.getU_addr_detail());
		}
		
		resUserData.setU_nick(updateUserData.getU_nick());
		resUserData.setU_id(currentUserData.getU_id());
		mServices.updateUserDetails(resUserData);
		return "redirect:/profile/edit";
		
	}
}
