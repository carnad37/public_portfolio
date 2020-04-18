package com.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.product.dto.Product;
import com.shop.admin.dto.Category;
import com.shop.product.dto.Ckeditor;
import com.shop.product.dto.Comment;
import com.shop.product.dto.CommentSub;



public interface ProductDAO {
	
	//��ǰ���������
	public int productAdd(Product product);
	
	//��ǰ�������б�
	public List<Product> productRead();
	
	//��ǰ������ ����
	public int productUpdate(Product product);
	
	//��ǰ������ ����
	public int productDelete(Product product);
	
	public List<Product> productSearchList(@Param("pagenum") int pagenum, @Param("contentnum") int contentnum, @Param("targetWord") String targetWord, @Param("c_id")int c_id);
	
	public List<Product> productList(@Param("pagenum") int pagenum, @Param("contentnum") int contentnum);
	
	public int totalCount();
	
	public Product productPage(int p_id);
	
	//u_id�� ��ǰ�˻�
	public List<Product> selectProductByU_id(@Param("u_id") int u_id, @Param("page") int page);
	
	public int countProductByU_id(int u_id);
	
	//u_id�� �ֹ��˻�
	public List<Product> selectOrderByU_id(@Param("u_id") int u_id, @Param("page") int page);
	
	public int countOrderByU_id(int u_id);
	
	//p_id����Ʈ�� ��ǰ �˻�
	public List<Product> getProductByCookie(@Param("list") List<Integer> targetList);
	
	//�ֱ� ��ϵ� ��ǰ 5��
	public List<Product> getRecentProduct();
	
	//��� ���
	public int insertQuestion(Comment comment);
	
	//��� ��������
	public List<Comment> loadQ_list(int p_id);
	
	//��� ����ѻ�� id ��������
	public List<CommentSub> get_pq_u_id(int p_id);
	
	//��� �亯 ���
	public int addReply(Comment comment);
	
	//ī�װ�����Ʈ
	public List<Category> getCategory();
	
	public int insertCkeditorImage(Ckeditor ckeditor);
	
 	public List<String> selectCkeditorDeleteImage(int u_id);
	
 	public int deleteCkeditorImageByU_id(int u_id);
	
	public int deleteCkeditorImageByP_id(int p_id);

	public int updateCkeditorState(Ckeditor ckeditor);
	
	public int insertProductReply(Comment comment);
	
	public List<Comment> getProductReplyList(int p_id);
}
