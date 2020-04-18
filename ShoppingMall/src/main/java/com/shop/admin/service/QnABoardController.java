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

	//���� ȭ��
	@RequestMapping(value = "/")
	public String adminIndex() {
		return "/_admin/DashBoard.admin-temp";
	}	
	
	//��� ������ ����
	@RequestMapping(value = "/QnA_view")
	public String viewQnABoard() {

		return "/_admin/QnA-Board.board-temp";
	}
	
	//��� ������ ����
	@RequestMapping(value = "/QnA_view", params = {"error"})
	public String viewErrorQnABoard(Model model, String error) {
		model.addAttribute("error", error);
		return "/_admin/QnA-Board.board-temp";
	}
	
	//��� ����
	@RequestMapping(value = "/QnA_del")
	public String delQnAReply(String que_id, RedirectAttributes redirect , @AuthenticationPrincipal CustomUser user) {
		//���� �������� �ʴ� que_id�϶�
		int retVal = adminService.delQnA_Post(user.getDetails().getU_id(), Integer.parseInt(que_id));
		if (retVal == 0) {
			redirect.addAttribute("error", "�߸��� ��û�Դϴ�.");
		}		
		return "redirect:/board/QnA_view";
	}
	
	//�Խù��� �ҷ����� Ajax��û ó��
	@RequestMapping(value = "/public")
	@ResponseBody
	public List<Map<String, Object>> publicBoard(@RequestParam("page") String page, Model model) {
		//���� ������ �ε��� * 10���� �ܾ�� limit�� ������ ����
		int nowPage = Integer.parseInt(page) * 10;
		
		List<QnA_Post> resultList = adminService.getQnA_publicPreData(nowPage);		
		
		List<Map<String, Object>> jsonArray = postToJSON(resultList);
		
		return jsonArray;
	}
	
	//Ư�� ���ù��� ���� ��û(ajax)�� ó��
	@RequestMapping(value = "/getPostData")
	@ResponseBody
	public List<QnA_Post> getPostData(
					@RequestParam("targetId") int targetId,
					@AuthenticationPrincipal CustomUser user) {
		List<QnA_Post> resultList = new ArrayList<QnA_Post>();
		
		System.out.println("targetId : " + targetId);
		
		//�������̵�� ���� id�� �˻��ؿ´�.
		//Post��ü�� �����.
		QnA_Post targetPost = new QnA_Post();
		//����� ���϶��� �����´�
		targetPost.setU_id(user.getDetails().getU_id());
		targetPost.setQue_id(targetId);
		
		//������ ����
		QnA_Post loadPost = adminService.getQnA_Post(targetPost);
		
		resultList.add(loadPost);
		
		//������ ��ϵǾ����� ���θ� Ȯ��.
		if (loadPost.getQue_check() == OK) {
			QnA_Post replyPost = new QnA_Post();
			replyPost.setQue_text(adminService.getQnAReply(targetId).getAns_text());
			resultList.add(replyPost);
		}		
		
		//List[0] = �����ڳ��� / List[1] = �����ڴ亯�� ��Ƽ� ����.
		
		return resultList;
	}
	
	//���� ���� �˻� Ajaxó��
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
	
	//��� ���� �� ó��(ajax)
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
	
	//��ǰ ����Ʈ�� Map���� ��ȯ
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
	
	//���� ����
	@RequestMapping(value = "/QnA_create")
	public String createQnABoard(
			QnA_Post post,
			RedirectAttributes redirect,
			@RequestParam(value="secret-control", required = false) String secret,
			@AuthenticationPrincipal CustomUser user) {
		//��б� ���� Ȯ��
		if (secret != null && secret.equals("secret")) {
			post.setQue_secret(isSecret);
		}
		
		//DB�� �Է�
		post.setU_id(user.getDetails().getU_id());
		int retVal = adminService.setQnA_Post(post);
		if (retVal != OK) {
			redirect.addAttribute("error", "��Ͽ� �����Ͽ����ϴ�.");
		}
		return "redirect:/board/QnA_view";
	}
	
	//���� �亯�ϱ�
	@RequestMapping(value = "/QnA_addReply")
	public String addQnAReply(
			Comment comment,
			RedirectAttributes redirect,
			@AuthenticationPrincipal CustomUser user) {		
		//�����ڸ� �����ؾ��Ѵ�. url-intercept�� ����.		
		//�ش� qna�� �亯�ķ� üũ
		adminService.setQnAReplyCheck(comment.getQue_id());
		
		//�亯 �߰�(�ش� ���̺��� que_id���� Unique�� ���̱⿡ ��ĥ ������ ����.)
		try {
			adminService.addQnAReply(comment);
		} catch (Exception e) {
			redirect.addAttribute("error", "�̹� �亯�� �Ϸ�� ���Դϴ�.");
		}
		
		return "redirect:/board/QnA_view";
	}
}
