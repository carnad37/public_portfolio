package com.shop.dao;

import java.util.List;
import java.util.Map;

import com.shop.admin.dto.Category;

public interface CategoryDAO {
	public int addCategory(String c_name);
	
	public int setCategory(Map<String, Object> cMap);
	
	public int delCategory(int c_id);
	
	public List<Category> getCategory();
}
