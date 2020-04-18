package com.shop.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.shop.admin.dto.SignUp_Data;
import com.shop.admin.dto.UserData;
import com.shop.admin.service.MemberServices;
import com.shop.main.security.role.CustomUser;
import com.shop.product.dto.Product;

@Controller
@RequestMapping(value = "/admin/member")
public class AdminMemberController {
	
	private final static int EMPTY = -1;
	
	@Autowired
	private MemberServices mServices;
	
	//��� �˻� �⺻ ������
	@RequestMapping(value = "/view")
	public String configView() {			
		return "/_admin/MemberControl.admin-temp";
	}
	
	//Ajax�� ���� �����͸� �ҷ��´�.
	@RequestMapping(value = "/loadSel")
	@ResponseBody
	public Map<String, Object> loadSelData(SignUp_Data targetDate,
												 @AuthenticationPrincipal CustomUser user) {
		targetDate.setU_id(user.getDetails().getU_id());
		targetDate.setPage(targetDate.getPage() * 10);
		
		Map<String, Object> jsonObject = new HashMap<String, Object>();
		List<SignUp_Data> loadData = mServices.loadUserSimpleDataSel(targetDate);
		List<Map<String, Object>> jsonArray = new ArrayList<Map<String, Object>>();	
		
		//��� ��ü�� �״�� �������൵ �ȴ�..
		for (SignUp_Data userData : loadData) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("u_id", userData.getU_id());
			data.put("u_account", userData.getU_account());
			data.put("u_nick", userData.getU_nick());
			data.put("u_mail", userData.getU_mail());
			data.put("u_name", userData.getU_name());
			data.put("u_phone", userData.getU_phone());
			jsonArray.add(data);
		}		
		
		if (loadData == null) {
			//������� ������ �׳� null�� ����
			return jsonObject;
		} else if (loadData.size() < 10) {
			//������� ������ 10���� �����ÿ� 10���� �Ų㺸���ش�.
			int loopNum = 10 - loadData.size();
			for (int i = 0; i < loopNum; i++) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("u_id", EMPTY);
				jsonArray.add(data);
			}
		}
		jsonObject.put("userList", jsonArray);
		
		//�Ʒ��� ������ ��ư�� 10������ ���� �ۼ��ǰ� �Ѵ�.
		if ((targetDate.getPage()/10) % 10 == 0) {
			jsonObject.put("totalRows", mServices.countTargetUser(targetDate));
			return jsonObject;
		}		
		
		return jsonObject;
	}
	//���� ������ ��ȯ���ش�.
	@RequestMapping(value = "/userInfo")
	@ResponseBody
	public UserData getUserInfo(String u_id) {
		int targetId = 0;
		
		if (!u_id.isEmpty()) {
			targetId = Integer.parseInt(u_id);
		} else {
			return null;
		}
		
		UserData user = mServices.getUserDetailByU_id(targetId);
		
		return user;
	}
	
	@RequestMapping(value = "/edit_process")
	public String profileEditProcess(SignUp_Data updateUserData, String u_id) {
		
		UserData resUserData = new UserData();
		
			resUserData.setU_nick(updateUserData.getU_nick());
			resUserData.setU_name(updateUserData.getU_name());
			resUserData.setU_zipcode(updateUserData.getU_zipcode());
			resUserData.setU_phone(updateUserData.getU_phone());
			resUserData.setU_addr(updateUserData.getU_addr());
			resUserData.setU_addr_detail(updateUserData.getU_addr_detail());
			resUserData.setU_id(updateUserData.getU_id());
		
		mServices.updateUserDetails(resUserData);
		return "redirect:/admin/member/view";
		
	}
	
//	��� ������ �ܾ���� ��Ʈ�ѷ�(deprecated)
//	@RequestMapping(value = "/loadAll")
//	@ResponseBody
//	public List<Map<String, Object>> loadAllData() {	
//		List<SignUp_Data> loadData = mServices.loadUserSimpleDataAll();
//		List<Map<String, Object>> jsonArray = new ArrayList<Map<String, Object>>();		
//		for (SignUp_Data userData : loadData) {
//			Map<String, Object> data = new HashMap<String, Object>();
//			data.put("u_id", userData.getU_id());
//			data.put("u_account", userData.getU_account());
//			data.put("u_nick", userData.getU_nick());
//			data.put("u_mail", userData.getU_mail());
//			data.put("u_name", userData.getU_name());
//			data.put("u_phone", userData.getU_phone());
//			jsonArray.add(data);
//		}				
//		return jsonArray;
//	}
	
}
	
	