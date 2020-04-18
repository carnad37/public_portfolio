package com.shop.admin.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.shop.admin.dto.Banner;
import com.shop.admin.dto.Comment;
import com.shop.admin.dto.QnA_Post;
import com.shop.admin.dto.UploadFileSet;
import com.shop.dao.AdminDAO;
import com.shop.main.service.CustomFileUplaod;

public class AdminServices implements AdminDAO{

	@Autowired
	CustomFileUplaod fileUpload;
	
	private AdminDAO dao;

	public AdminServices(AdminDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public int setQnAReplyCheck(int que_id) {
		// TODO Auto-generated method stub
		return dao.setQnAReplyCheck(que_id);
	}
	
	public List<QnA_Post> getQnA_AllData(int nowPage) {
		return dao.getQnA_AllData(nowPage);
	}
	
	@Override
	public Comment getQnAReply(int que_id) {
		// TODO Auto-generated method stub
		return dao.getQnAReply(que_id);
	}
	
	@Override
	public int addQnAReply(Comment comment) {
		// TODO Auto-generated method stub
		return dao.addQnAReply(comment);
	}

	@Override
	public List<QnA_Post> getQnA_publicPreData(int nowPage) {
		// TODO Auto-generated method stub
		return dao.getQnA_publicPreData(nowPage);
	}

	@Override
	public List<QnA_Post> getQnA_SecretPreData(Map<String, Object> nowData) {
		// TODO Auto-generated method stub
		return dao.getQnA_SecretPreData(nowData);
	}

	@Override
	public int setQnA_Post(QnA_Post post) {
		String preText = post.getQue_text();
		post.setQue_text(preText.replace("\r\n", "<br>"));
		return dao.setQnA_Post(post);
	}
	
	//복수 파일 업데이트 서비스
	public List<Banner> multiUploadService(List<MultipartFile> fileList, String path) throws IOException {
		List<Banner> bannerList = new ArrayList<Banner>();
		for (MultipartFile file : fileList) {
			
			UploadFileSet fileSet = fileUpload.fileUpload(file, path);		
			//새 배너 데이터
			Banner banner = new Banner();
			String originFileName = fileSet.getOriginFileName();
			banner.setRb_name(fileSet.getOriginFileName().substring(0, originFileName.lastIndexOf(".")));
			banner.setRb_path(fileSet.getSaveFileName());
			bannerList.add(banner);
		}		
		return bannerList;
	}
	
	
	@Override
	public List<QnA_Post> getQnA_SecretSearchData(Map<String, Object> nowData) {
		// TODO Auto-generated method stub
		return dao.getQnA_SecretSearchData(nowData);
	}

	@Override
	public List<QnA_Post> getQnA_PublicSearchData(Map<String, Object> nowData) {
		// TODO Auto-generated method stub
		return dao.getQnA_PublicSearchData(nowData);
	}

	@Override
	public QnA_Post getQnA_Post(QnA_Post post) {
		// TODO Auto-generated method stub
		return dao.getQnA_Post(post);
	}

	@Override
	public int delQnA_Post(int u_id, int que_id) {
		// TODO Auto-generated method stub
		return dao.delQnA_Post(u_id, que_id);
	}
	
}
