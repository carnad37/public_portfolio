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
		  ���� DB�� ī�װ��� �о�´�.
		  �о�� ī�װ��� ����ϰ� �߰����� ���θ� �����Ѵ�.
		 
		  ��� : 
		  	1.������ ī�װ��� ����.
		  	2.������ ī�װ��� ����.
		  	3.���ο� ī�װ��� �߰�.
		 */
		//ī�װ� ȹ��
		
		List<Category> categoryList = cService.getCategory();
		model.addAttribute("categoryList", categoryList);
				
		return "/_admin/Category.admin-temp";
	}
	
	//�Ķ���ͷ� error�� ���� ���޵ɰ��, modal�� ����ش�.
	@RequestMapping(value = "/view", params = {"error"})
	public String editCategory(@RequestParam String error, Model model) {

		List<Category> categoryList = cService.getCategory();
		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("error", error);
		
		return "/_admin/Category.admin-temp";
	}

	//Ÿ�� ī�װ� ����.
	@RequestMapping(value = "/delete")
	public String deleteCategory(@RequestParam int c_id) {
		
		cService.delCategory(c_id);					
		return "redirect:/admin/category/view";
	}
	
	//Ÿ�� ī�װ��� ����.
	@RequestMapping(value = "/modify")
	public String modifyCategory(@RequestParam int c_id, @RequestParam String c_name, RedirectAttributes attribute) {
		//ī�װ����� null,"" ���� �ƴѰ��
		if (!c_name.isEmpty()) {
			Map<String, Object> cMap = new HashMap<String, Object>();		
			cMap.put("c_id", c_id);
			cMap.put("c_name", c_name);
			cService.setCategory(cMap);
		} else {
			attribute.addAttribute("error", "���� �Էµ��� �ʾҽ��ϴ�.");
		}

		return "redirect:/admin/category/view";
	}
	
	//���ο� ī�װ� ����
	@RequestMapping(value = "/create")
	public String createCategory(@RequestParam String c_name, RedirectAttributes attribute) {
		
		//ī�װ����� null,"" ���� �ƴѰ��
		if (!c_name.isEmpty()) {
			cService.addCategory(c_name);	
		} else {
			attribute.addAttribute("error", "���� �Էµ��� �ʾҽ��ϴ�.");
		}
		
		return "redirect:/admin/category/view";
	}
	
}
