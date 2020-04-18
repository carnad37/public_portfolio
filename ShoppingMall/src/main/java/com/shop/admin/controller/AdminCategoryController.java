package com.shop.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.admin.dto.Category;
import com.shop.admin.service.CategoryServices;

@Controller
@RequestMapping(value = "/admin/category")
public class AdminCategoryController {

	@Autowired
	CategoryServices cService;

	@RequestMapping(value = "/view")
	public String editCategory(Model model) {
		/*
		  먼저 DB의 카테고리를 읽어온다.
		  읽어온 카테고리를 출력하고 추가할지 여부를 결정한다.
		 
		  기능 : 
		  	1.기존의 카테고리를 삭제.
		  	2.기존의 카테고리명 수정.
		  	3.새로운 카테고리를 추가.
		 */
		//카테고리 획득
		
		List<Category> categoryList = cService.getCategory();
		model.addAttribute("categoryList", categoryList);
				
		return "/_admin/Category.admin-temp";
	}
	
	//파라미터로 error가 같이 전달될경우, modal을 띄워준다.
	@RequestMapping(value = "/view", params = {"error"})
	public String editCategory(@RequestParam String error, Model model) {

		List<Category> categoryList = cService.getCategory();
		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("error", error);
		
		return "/_admin/Category.admin-temp";
	}

	//타겟 카테고리 삭제.
	@RequestMapping(value = "/delete")
	public String deleteCategory(@RequestParam int c_id) {
		
		cService.delCategory(c_id);					
		return "redirect:/admin/category/view";
	}
	
	//타겟 카테고리명 변경.
	@RequestMapping(value = "/modify")
	public String modifyCategory(@RequestParam int c_id, @RequestParam String c_name, RedirectAttributes attribute) {
		//카테고리명이 null,"" 값이 아닌경우
		if (!c_name.isEmpty()) {
			Map<String, Object> cMap = new HashMap<String, Object>();		
			cMap.put("c_id", c_id);
			cMap.put("c_name", c_name);
			cService.setCategory(cMap);
		} else {
			attribute.addAttribute("error", "값이 입력되지 않았습니다.");
		}

		return "redirect:/admin/category/view";
	}
	
	//새로운 카테고리 생성
	@RequestMapping(value = "/create")
	public String createCategory(@RequestParam String c_name, RedirectAttributes attribute) {
		
		//카테고리명이 null,"" 값이 아닌경우
		if (!c_name.isEmpty()) {
			cService.addCategory(c_name);	
		} else {
			attribute.addAttribute("error", "값이 입력되지 않았습니다.");
		}
		
		return "redirect:/admin/category/view";
	}
	
}
