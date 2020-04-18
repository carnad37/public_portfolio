package com.shop.admin.service;

import java.util.List;
import java.util.Map;

import com.shop.admin.dto.Category;
import com.shop.dao.CategoryDAO;

public class CategoryServices implements CategoryDAO {

	private CategoryDAO dao;
	
	public CategoryServices(CategoryDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public int addCategory(String c_name) {
		return dao.addCategory(c_name);
	}

	@Override
	public int setCategory(Map<String, Object> cMap) {
		return dao.setCategory(cMap);
	}

	@Override
	public int delCategory(int c_id) {
		return dao.delCategory(c_id);
	}

	@Override
	public List<Category> getCategory() {
		return dao.getCategory();
	}

}
