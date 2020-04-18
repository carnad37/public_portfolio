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
	
	//멤버 검색 기본 페이지
	@RequestMapping(value = "/view")
	public String configView() {			
		return "/_admin/MemberControl.admin-temp";
	}
	
	//Ajax로 유저 데이터를 불러온다.
	@RequestMapping(value = "/loadSel")
	@ResponseBody
	public Map<String, Object> loadSelData(SignUp_Data targetDate,
												 @AuthenticationPrincipal CustomUser user) {
		targetDate.setU_id(user.getDetails().getU_id());
		targetDate.setPage(targetDate.getPage() * 10);
		
		Map<String, Object> jsonObject = new HashMap<String, Object>();
		List<SignUp_Data> loadData = mServices.loadUserSimpleDataSel(targetDate);
		List<Map<String, Object>> jsonArray = new ArrayList<Map<String, Object>>();	
		
		//사실 객체를 그대로 전달해줘도 된다..
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
			//결과값이 없을시 그냥 null값 리턴
			return jsonObject;
		} else if (loadData.size() < 10) {
			//결과값이 있으나 10보다 작을시엔 10개로 매꿔보내준다.
			int loopNum = 10 - loadData.size();
			for (int i = 0; i < loopNum; i++) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("u_id", EMPTY);
				jsonArray.add(data);
			}
		}
		jsonObject.put("userList", jsonArray);
		
		//아래의 페이지 버튼은 10단위로 새로 작성되게 한다.
		if ((targetDate.getPage()/10) % 10 == 0) {
			jsonObject.put("totalRows", mServices.countTargetUser(targetDate));
			return jsonObject;
		}		
		
		return jsonObject;
	}
	//유저 정보를 반환해준다.
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
	
//	모든 데이터 긁어오는 컨트롤러(deprecated)
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
	
	