//package com.shop.admin.controller;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMessage.RecipientType;
//import javax.servlet.http.HttpServletRequest;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.shop.admin.dto.QnA_Post;
//import com.shop.admin.service.AdminServices;
//import com.shop.dao.ConfigurationDAO;
//
///**
// * 
// * 
// * 기능 추가 테스트용 컨트롤러.
// * 업데이트가 없을땐 비활성화 해둔다.
// * 
// * 
// */
//
//@Controller
//@RequestMapping(value = "/admin/func")
//public class AdminSubController {
//	
//	private static final Logger logger = LoggerFactory.getLogger(AdminSubController.class);
//	
//	@Autowired
//	AdminServices service;
//	
//	@Autowired
//	private JavaMailSenderImpl javaMailSenderImpl;
//	
//	@Autowired
//	private ConfigurationDAO dao;
//	
//	@RequestMapping(value = "/testQuery")
//	public String testCode(Model model) {
//		List<Integer> test = new ArrayList<Integer>();
//		test.add(1);
//		test.add(2);
//		dao.delBannerImages(test);
//		return "/admin/index.admin-temp";
//	}
//	
//	
//	/**
//	 * Simply selects the home view to render by returning its name.
//	 */
//	@RequestMapping(value = "/")
//	public String adminIndex(Model model) {
//		System.out.println("admin adminIndex");
//		return "/admin/index.admin-temp";
//	}
//	
//	@RequestMapping(value = "/secret")
//	public String secretBoard(@RequestParam("page") String page, @RequestParam("u_id") String u_id, Model model) {
//		int nowPage = Integer.parseInt(page) * 10;
//		int target_id = Integer.parseInt(u_id);
//		HashMap<String, Integer> nowData = new HashMap<String, Integer>();
//		nowData.put("nowPage", nowPage);
//		nowData.put("target_id", target_id);
////		List<QnA_Post> resultList = service.getQnA_SecretPreData(nowData);
//		
////		model = inputJSON(resultList, nowPage ,model);
//				
//		return "/admin/qna-page";
//	}
//	
//	@RequestMapping(value = "/public")
//	public String publicBoard(@RequestParam("page") String page, Model model) {
//		int nowPage = Integer.parseInt(page) * 10;
//		List<QnA_Post> resultList = service.getQnA_publicPreData(nowPage);
//
//		model = inputJSON(resultList, nowPage ,model);
//		
//		return "/admin/qna-page";
//	}
//	
//	@RequestMapping(value = "/test_view")
//	@ResponseBody
//	public List<Map<String, Object>> testView(@RequestParam("page") String page, Model model) {
//		int nowPage = Integer.parseInt(page) * 10;
//		List<QnA_Post> resultList = service.getQnA_publicPreData(nowPage);
//		
//		List<Map<String, Object>> jsonArray = new ArrayList<Map<String, Object>>();
//		
//		for (QnA_Post post : resultList) {
//			Map<String, Object> data = new HashMap<String, Object>();
//			data.put("no", post.getNo());
//			data.put("que_id", post.getQue_id());
////			data.put("u_id", post.getU_id());
////			data.put("que_secret", post.getQue_secret());
//			data.put("que_title", post.getQue_title());
////			data.put("que_text", post.getQue_text());
//			data.put("que_date", post.getQue_date());
//			data.put("que_check", post.getQue_check());
//			jsonArray.add(data);
//		}
//				
//		return jsonArray;
//	}
//	
//	private Model inputJSON(List<QnA_Post> resultList, int page, Model model) {		
//		
//		JSONArray jsonResult = new JSONArray();
//				
//		for (QnA_Post post : resultList) {
//			JSONObject data = new JSONObject();
//			data.put("no", post.getNo());
//			data.put("que_id", post.getQue_id());
////			data.put("u_id", post.getU_id());
////			data.put("que_secret", post.getQue_secret());
//			data.put("que_title", post.getQue_title());
////			data.put("que_text", post.getQue_text());
//			data.put("que_date", post.getQue_date());
//			data.put("que_check", post.getQue_check());
//			jsonResult.add(data);
//		}
//		
//		if (page == 0) {
//			JSONObject data = new JSONObject();
//			data.put("rows", resultList.get(0).getNo());
//			jsonResult.add(data);
//		}
//				
//		model.addAttribute("resultList", jsonResult);
//		
//		return model;
//	}
//	
//	@RequestMapping(value = "/view")
//	public String viewBoard(Model model) {
//
//		return "/admin/board-qna.board-temp";
//	}
//	
//	@RequestMapping(value = "/write")
//	public String writeBoard(@RequestParam("que_id") String que_id, Model model) {		
//		
//		return "/admin/board-qna.board-temp";
//	}
//	
//	@RequestMapping(value = "/register")
//	public String registerUser(@RequestParam("que_id") String que_id, Model model) {		
//		
//		
//		return "/admin/board-qna.board-temp";
//	}
//
//}
