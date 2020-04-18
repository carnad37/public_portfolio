package com.shop.main.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.admin.dto.Category;
import com.shop.admin.dto.SiteConfiguration;
import com.shop.admin.service.AdminServices;
import com.shop.admin.service.CategoryServices;
import com.shop.main.security.role.CustomUser;
import com.shop.product.dto.Product;
import com.shop.product.service.ProductService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	BCryptPasswordEncoder encodePassword;
	
//	@Autowired
//	AdminServices aServices;
	
	@Autowired
	CategoryServices cServices;
	
	@Autowired
	SiteConfiguration config;
	
	@Autowired
	ProductService pServices;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model,
					   @CookieValue(value="recentProduct", required=false) String recentProduct) {
		List<String> onBannerList = config.getOnBannerList();
		int bannerNumber = config.getBannerNumber();
		int onBannerNumber = onBannerList.size();
		
		if (bannerNumber > onBannerNumber) {
			int subNum = bannerNumber - onBannerNumber;
			for (int i = 0; i < subNum; i++) {
				onBannerList.add("1900x1080.png");
			}
		}
		model.addAttribute("bannerList", onBannerList);
		model.addAttribute("bannerNumber", bannerNumber);
		
		//최근 본 상품
		List<Product> cookieList = null;
		if(recentProduct != null) {
			List<Integer> targetList = new ArrayList<Integer>();
			for (String data : recentProduct.split("_")) {
				targetList.add(Integer.parseInt(data));
			}
			cookieList = pServices.getProductByCookie(targetList);
		}
		model.addAttribute("cookieList", cookieList);
		
		//최근 등록된 상품
		List<Product> addProductList = pServices.getRecentProduct();
		model.addAttribute("addProductList", addProductList);
		
		return "/_main/main-page.main-temp";
	}
	
	@RequestMapping(value = "/getCategory")
	@ResponseBody
	public List<Category> getCategory(Model model) {
		List<Category> categoryList = cServices.getCategory();
		return categoryList;
	}


}
