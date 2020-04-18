package com.shop.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.admin.dto.CustomRememberMeToken;
import com.shop.admin.dto.SignUp_Data;
import com.shop.admin.dto.UserData;
import com.shop.dao.MemberDAO;
import com.shop.main.security.role.CustomUser;

public class MemberServices implements MemberDAO{

	public MemberServices(MemberDAO dao) {
		this.dao = dao;
	}
	
	private MemberDAO dao;
	
	
	@Override
	public UserData getUserDetailByU_id(int u_id) {
		// TODO Auto-generated method stub
		return dao.getUserDetailByU_id(u_id);
	}
	
	@Override
	public int disableUser(int u_id) {
		// TODO Auto-generated method stub
		return dao.disableUser(u_id);
	}
	
	@Override
	public int countTargetUser(SignUp_Data searchData) {
		// TODO Auto-generated method stub
		return dao.countTargetUser(searchData);
	}
	
	@Override
	public CustomUser getUserSimpleByAccount(String account) {
		// TODO Auto-generated method stub
		return dao.getUserSimpleByAccount(account);
	}

	@Override
	public CustomUser getUserDetailByAccount(String account) {
		// TODO Auto-generated method stub
		return dao.getUserDetailByAccount(account);
	}

	@Override
	public List<SignUp_Data> loadUserSimpleDataAll() {
		// TODO Auto-generated method stub
		return dao.loadUserSimpleDataAll();
	}

	@Override
	public List<SignUp_Data> loadUserSimpleDataSel(SignUp_Data searchData) {
		// TODO Auto-generated method stub
		return dao.loadUserSimpleDataSel(searchData);
	}

	@Override
	public int updateUserDetails(UserData resData) {
		// TODO Auto-generated method stub
		return dao.updateUserDetails(resData);
	}

	public int setUserRole(Map<String, Object> userRoleData) {
		// TODO Auto-generated method stub
		return dao.setUserRole(userRoleData);
	}

	@Override
	public int modifyNewUserRole(Map<String, Object> userRoleData) {
		// TODO Auto-generated method stub
		return dao.modifyNewUserRole(userRoleData);
	}

	@Override
	public int setUserData(SignUp_Data userData) {
		// TODO Auto-generated method stub
		return dao.setUserData(userData);
	}

	@Override
	public int setUserRoleData(Map<String, Object> userRoleData) {
		// TODO Auto-generated method stub
		return dao.setUserRoleData(userRoleData);
	}
	
	@Override
	public int countRowById(String id) {
		// TODO Auto-generated method stub
		return dao.countRowById(id);
	}
	
	@Override
	public int countRowByNick(String id) {
		return dao.countRowByNick(id);
	}

	
	
}
