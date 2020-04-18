package com.shop.product.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.admin.dto.Category;
import com.shop.dao.ProductDAO;

import com.shop.product.dto.Product;
import com.shop.product.dto.Ckeditor;
import com.shop.product.dto.Comment;
import com.shop.product.dto.CommentSub;



public class ProductService implements ProductDAO {
	
	private ProductDAO dao;
	
	public ProductService(ProductDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Product> getRecentProduct() {
		// TODO Auto-generated method stub
		return dao.getRecentProduct();
	}
	
	@Override
	public List<Product> getProductByCookie(List<Integer> targetList) {
		// TODO Auto-generated method stub
		return dao.getProductByCookie(targetList);
	}
	
	@Override
	public int countOrderByU_id(int u_id) {
		// TODO Auto-generated method stub
		return dao.countOrderByU_id(u_id);
	}
	
	@Override
	public List<Product> selectOrderByU_id(int u_id, int page) {
		// TODO Auto-generated method stub
		return dao.selectOrderByU_id(u_id, page);
	}

	@Override
	public int countProductByU_id(int u_id) {
		// TODO Auto-generated method stub
		return dao.countProductByU_id(u_id);
	}
	
	@Override
	public List<Product> selectProductByU_id(int u_id, int page) {
		// TODO Auto-generated method stub
		return dao.selectProductByU_id(u_id, page);
	}
	
	@Override
	public int productAdd(Product product)
	{
		return dao.productAdd(product);
	}

	@Override
	public List<Product> productRead()
	{
		return dao.productRead();
	}	
	
	@Override
	public int productUpdate(Product product)
	{
		return dao.productUpdate(product);
	}
	
	@Override
	public int productDelete(Product product)
	{
		return dao.productDelete(product);
	}
	
//	@Override
//	public List<Product> productList()
//	{
//		return dao.productList();
//	}
//	
	
	@Override
	public List<Product> productList(@Param("pagenum") int pagenum, @Param("contentnum") int contentnum)
	{
		return dao.productList(pagenum, contentnum);
	}
	
	@Override
	public int totalCount()
	{
		return dao.totalCount();
	}
	
	@Override
	public Product productPage(int p_id)
	{
		return dao.productPage(p_id);
	}
	
	@Override
	public int insertQuestion(Comment comment)
	{
		return dao.insertQuestion(comment);
	}
	
	@Override
	public List<Comment> loadQ_list(int p_id)
	{
		return dao.loadQ_list(p_id);
	}
	
	@Override
	public List<CommentSub> get_pq_u_id(int p_id)
	{
		return dao.get_pq_u_id(p_id);
	}
	
	@Override
	public int addReply(Comment comment) {
		return dao.addReply(comment);
	}
	
	@Override
	public List<Category> getCategory()	{
		return dao.getCategory();
	}

	@Override
	public int insertCkeditorImage(Ckeditor ckeditor) {
		return dao.insertCkeditorImage(ckeditor);
	}

	@Override
	public int deleteCkeditorImageByU_id(int u_id) {
		return dao.deleteCkeditorImageByU_id(u_id);
	}

	@Override
	public int deleteCkeditorImageByP_id(int p_id) {
		return dao.deleteCkeditorImageByP_id(p_id);
	}

	@Override
	public List<String> selectCkeditorDeleteImage(int u_id) {
		return dao.selectCkeditorDeleteImage(u_id);
	}

	@Override
	public int updateCkeditorState(Ckeditor ckeditor) {
		// TODO Auto-generated method stub
		return dao.updateCkeditorState(ckeditor);
	}

	@Override
	public List<Product> productSearchList(int pagenum, int contentnum, String targetWord, int c_id) {
		return dao.productSearchList(pagenum, contentnum, targetWord, c_id);
	}
	
	@Override
	public int insertProductReply(Comment comment) {
		// TODO Auto-generated method stub
		return dao.insertProductReply(comment);
	}
	
	@Override
	public List<Comment> getProductReplyList(int p_id) {
		// TODO Auto-generated method stub
		return dao.getProductReplyList(p_id);
	}
	
	public String cookieSetting(String currentData, String newData) {
		String divString = "_";
		String[] cookieArray = currentData.split(divString);

		if (cookieArray.length < 5) {
			return currentData + divString + newData;
		} else {
			
			//같은 쿠키 체크
			for (String data : cookieArray) {

				if (data.equals(newData)) {
					return currentData;
				}
			}
			
			//쿠키 내용 변경
			int overNumber = cookieArray.length - 5;
			StringBuilder newCookie = new StringBuilder();
			for (int i = overNumber + 1; i < cookieArray.length; i++) {
				newCookie.append(cookieArray[i]);
				newCookie.append(divString);
			}
			newCookie.append(newData);
			return newCookie.toString();
		}		
	}
	
}
