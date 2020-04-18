package com.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shop.admin.dto.Comment;
import com.shop.admin.dto.QnA_Post;
import com.shop.admin.dto.SignUp_Data;
import com.shop.admin.dto.UserData;
import com.shop.main.security.role.CustomUser;

public interface AdminDAO {
	
	public List<QnA_Post> getQnA_AllData(int nowPage);
	
	public List<QnA_Post> getQnA_SecretPreData(Map<String, Object> nowData);	
	
	public List<QnA_Post> getQnA_publicPreData(int nowPage);
	
	public int setQnA_Post(QnA_Post post);		

	public List<QnA_Post> getQnA_SecretSearchData(Map<String, Object> nowData);	
	
	public List<QnA_Post> getQnA_PublicSearchData(Map<String, Object> nowData);

	public QnA_Post getQnA_Post(QnA_Post post);
	
	public int delQnA_Post(@Param("u_id") int u_id, @Param("que_id") int que_id);
	
	public int addQnAReply(Comment comment);
	
	public Comment getQnAReply(int que_id);
	
	public int setQnAReplyCheck(int que_id);
}
