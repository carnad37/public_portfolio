package com.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.product.dto.Product;
import com.shop.admin.dto.Category;
import com.shop.product.dto.Ckeditor;
import com.shop.product.dto.Comment;
import com.shop.product.dto.CommentSub;



public interface ProductDAO {
	
	//상품페이지등록
	public int productAdd(Product product);
	
	//상품페이지읽기
	public List<Product> productRead();
	
	//상품페이지 수정
	public int productUpdate(Product product);
	
	//상품페이지 삭제
	public int productDelete(Product product);
	
	public List<Product> productSearchList(@Param("pagenum") int pagenum, @Param("contentnum") int contentnum, @Param("targetWord") String targetWord, @Param("c_id")int c_id);
	
	public List<Product> productList(@Param("pagenum") int pagenum, @Param("contentnum") int contentnum);
	
	public int totalCount();
	
	public Product productPage(int p_id);
	
	//u_id로 상품검색
	public List<Product> selectProductByU_id(@Param("u_id") int u_id, @Param("page") int page);
	
	public int countProductByU_id(int u_id);
	
	//u_id로 주문검색
	public List<Product> selectOrderByU_id(@Param("u_id") int u_id, @Param("page") int page);
	
	public int countOrderByU_id(int u_id);
	
	//p_id리스트로 상품 검색
	public List<Product> getProductByCookie(@Param("list") List<Integer> targetList);
	
	//최근 등록된 상품 5개
	public List<Product> getRecentProduct();
	
	//댓글 등록
	public int insertQuestion(Comment comment);
	
	//댓글 가져오기
	public List<Comment> loadQ_list(int p_id);
	
	//댓글 등록한사람 id 가져오기
	public List<CommentSub> get_pq_u_id(int p_id);
	
	//댓글 답변 등록
	public int addReply(Comment comment);
	
	//카테고리리스트
	public List<Category> getCategory();
	
	public int insertCkeditorImage(Ckeditor ckeditor);
	
 	public List<String> selectCkeditorDeleteImage(int u_id);
	
 	public int deleteCkeditorImageByU_id(int u_id);
	
	public int deleteCkeditorImageByP_id(int p_id);

	public int updateCkeditorState(Ckeditor ckeditor);
	
	public int insertProductReply(Comment comment);
	
	public List<Comment> getProductReplyList(int p_id);
}
