package com.shop.dao;

import java.util.List;
import java.util.Map;

import com.shop.admin.dto.SignUp_Data;
import com.shop.admin.dto.UserData;
import com.shop.main.security.role.CustomUser;

public interface MemberDAO {


	public CustomUser getUserSimpleByAccount(String account);
	
	public CustomUser getUserDetailByAccount(String account);
//	public UserData getUserDetailByAccount(String account);
	
	public int countTargetUser(SignUp_Data searchData);
	
	public List<SignUp_Data> loadUserSimpleDataAll();

	public List<SignUp_Data> loadUserSimpleDataSel(SignUp_Data searchData);

	public int updateUserDetails(UserData resData);
	
	public int countRowById(String id);
	
	public int countRowByNick(String id);
	
	public int setUserRole(Map<String, Object> userRoleData);
	
	public int modifyNewUserRole(Map<String, Object> userRoleData);
	
	public int setUserData(SignUp_Data userData);
	
	public int setUserRoleData(Map<String, Object> userRoleData);
	
	public int disableUser(int u_id);
	
	public UserData getUserDetailByU_id(int u_id);
}
