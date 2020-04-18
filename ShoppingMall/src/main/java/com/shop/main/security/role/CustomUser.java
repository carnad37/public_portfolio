package com.shop.main.security.role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shop.admin.dto.UserData;


public class CustomUser implements UserDetails{

	private static final long serialVersionUID = 1L;

	private List<GrantedAuthority> authorities;
	private String enabled;
	private UserData details = new UserData();
	
//	private boolean accountNonExpired = true;
//	private boolean accountNonLocked = true;
//	private boolean credentialsNonExpired = true;
	
	public void setU_name(String u_name) {
		this.details.setU_name(u_name);
	}

	public void setU_id(int u_id) {
		this.details.setU_id(u_id);
	}

	public void setU_nick(String u_nick) {
		this.details.setU_nick(u_nick);
	}

	public void setU_mail(String u_mail) {
		this.details.setU_mail(u_mail);
	}

	public void setU_phone(String u_phone) {
		this.details.setU_phone(u_phone);
	}

	public void setU_zipcode(String u_zipcode) {
		this.details.setU_zipcode(u_zipcode);
	}

	public void setU_addr(String u_addr) {
		this.details.setU_addr(u_addr);
	}

	public void setU_addr_detail(String u_addr_detail) {
		this.details.setU_addr_detail(u_addr_detail);
	}	

	public UserData getDetails() {
		return this.details;
	}

	public void setAuthorities(String role) {
		if (authorities == null) {
			this.authorities = new ArrayList<GrantedAuthority>();
		}
		this.authorities.add(new SimpleGrantedAuthority(role));
	}

	private String u_account;
	private String u_password;

	public void setU_account(String u_account) {
		this.u_account = u_account;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return u_password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return u_account;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		if (this.enabled.toUpperCase().equals("Y")) {
			return true;
		} else {
			return false;
		}
	}

}
