package com.shop.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.admin.controller.AdminController;
import com.shop.admin.dto.Comment;
import com.shop.admin.dto.QnA_Post;
import com.shop.main.security.role.CustomUser;

@Controller
@RequestMapping(value = "/board")
public class QnABoardController {
	private static final Logger logger = LoggerFactory.getLogger(QnABoardController.class);
	private static final int isSecret = 1;
	private static final int OK = 1;

	@Autowired
	AdminServices adminService;

	//메인 화면
	@RequestMapping(value = "/")
	public String adminIndex() {
		return "/_admin/DashBoard.admin-temp";
	}	
	
	//상담 개시판 메인
	@RequestMapping(value = "/QnA_view")
	public String viewQnABoard() {

		return "/_admin/QnA-Board.board-temp";
	}
	
	//상담 개시판 메인
	@RequestMapping(value = "/QnA_view", params = {"error"})
	public String viewErrorQnABoard(Model model, String error) {
		model.addAttribute("error", error);
		return "/_admin/QnA-Board.board-temp";
	}
	
	//상담 삭제
	@RequestMapping(value = "/QnA_del")
	public String delQnAReply(String que_id, RedirectAttributes redirect , @AuthenticationPrincipal CustomUser user) {
		//만약 존재하지 않는 que_id일때
		int retVal = adminService.delQnA_Post(user.getDetails().getU_id(), Integer.parseInt(que_id));
		if (retVal == 0) {
			redirect.addAttribute("error", "잘못된 요청입니다.");
		}		
		return "redirect:/board/QnA_view";
	}
	
	//게시물을 불러오는 Ajax요청 처리
	@RequestMapping(value = "/public")
	@ResponseBody
	public List<Map<String, Object>> publicBoard(@RequestParam("page") String page, Model model) {
		//현재 페이지 인덱스 * 10으로 긁어올 limit의 시작점 결정
		int nowPage = Integer.parseInt(page) * 10;
		
		List<QnA_Post> resultList = adminService.getQnA_publicPreData(nowPage);		
		
		List<Map<String, Object>> jsonArray = postToJSON(resultList);
		
		return jsonArray;
	}
	
	//특정 개시물의 내용 요청(ajax)을 처리
	@RequestMapping(value = "/getPostData")
	@ResponseBody
	public List<QnA_Post> getPostData(
					@RequestParam("targetId") int targetId,
					@AuthenticationPrincipal CustomUser user) {
		List<QnA_Post> resultList = new ArrayList<QnA_Post>();
		
		System.out.println("targetId : " + targetId);
		
		//유저아이디와 질문 id로 검색해온다.
		//Post객체를 만든다.
		QnA_Post targetPost = new QnA_Post();
		//비공개 글일때도 가져온다
		targetPost.setU_id(user.getDetails().getU_id());
		targetPost.setQue_id(targetId);
		
		//쿼리를 보냄
		QnA_Post loadPost = adminService.getQnA_Post(targetPost);
		
		resultList.add(loadPost);
		
		//리플이 등록되었는지 여부를 확인.
		if (loadPost.getQue_check() == OK) {
			QnA_Post replyPost = new QnA_Post();
			replyPost.setQue_text(adminService.getQnAReply(targetId).getAns_text());
			resultList.add(replyPost);
		}		
		
		//List[0] = 질문자내용 / List[1] = 관리자답변을 담아서 전달.
		
		return resultList;
	}
	
	//공개 상담글 검색 Ajax처리
	@RequestMapping(value = "/public_search")
	@ResponseBody
	public List<Map<String, Object>> publicSearchBoard(@RequestParam("page") String page,
													   @RequestParam("search-target") String target,
													   @RequestParam("search-option") String option) {
		int nowPage = Integer.parseInt(page) * 10;
		
		Map<String, Object> nowData = new HashMap<String, Object>();
		nowData.put("que_title", target);
		nowData.put("que_text", target);
		nowData.put("nowPage", nowPage);
		nowData.put("option", Integer.parseInt(option));
		List<QnA_Post> resultList = adminService.getQnA_PublicSearchData(nowData);

		List<Map<String, Object>> jsonArray = postToJSON(resultList);
	
		return jsonArray;
	}
	
	//비밀 상담글 탭 처리(ajax)
	@RequestMapping(value = "/secret")
	@ResponseBody
	public List<Map<String, Object>> secretBoard(@RequestParam("page") String page, @AuthenticationPrincipal CustomUser user) {
		int nowPage = Integer.parseInt(page) * 10;
		
		HashMap<String, Object> nowData = new HashMap<String, Object>();
		nowData.put("nowPage", nowPage);
		nowData.put("target_id", user.getDetails().getU_id());
		
		List<QnA_Post> resultList = adminService.getQnA_SecretPreData(nowData);		
		List<Map<String, Object>> jsonArray = postToJSON(resultList);
		
		return jsonArray;
	}
	
	//상품 리스트를 Map으로 변환
	private List<Map<String, Object>> postToJSON(List<QnA_Post> resultList) {
		List<Map<String, Object>> jsonArray = new ArrayList<Map<String, Object>>();		
		for (QnA_Post post : resultList) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("no", post.getNo());
			data.put("que_id", post.getQue_id());
			data.put("que_title", post.getQue_title());
			data.put("que_date", post.getQue_date());
			data.put("que_check", post.getQue_check());
			jsonArray.add(data);
		}				
		return jsonArray;
	}
	
	//상담글 생성
	@RequestMapping(value = "/QnA_create")
	public String createQnABoard(
			QnA_Post post,
			RedirectAttributes redirect,
			@RequestParam(value="secret-control", required = false) String secret,
			@AuthenticationPrincipal CustomUser user) {
		//비밀글 여부 확인
		if (secret != null && secret.equals("secret")) {
			post.setQue_secret(isSecret);
		}
		
		//DB에 입력
		post.setU_id(user.getDetails().getU_id());
		int retVal = adminService.setQnA_Post(post);
		if (retVal != OK) {
			redirect.addAttribute("error", "등록에 실패하였습니다.");
		}
		return "redirect:/board/QnA_view";
	}
	
	//상담글 답변하기
	@RequestMapping(value = "/QnA_addReply")
	public String addQnAReply(
			Comment comment,
			RedirectAttributes redirect,
			@AuthenticationPrincipal CustomUser user) {		
		//관리자만 가능해야한다. url-intercept에 설정.		
		//해당 qna를 답변후로 체크
		adminService.setQnAReplyCheck(comment.getQue_id());
		
		//답변 추가(해당 테이블의 que_id값은 Unique한 값이기에 겹칠 염려는 없다.)
		try {
			adminService.addQnAReply(comment);
		} catch (Exception e) {
			redirect.addAttribute("error", "이미 답변이 완료된 글입니다.");
		}
		
		return "redirect:/board/QnA_view";
	}
}
