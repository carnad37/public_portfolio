package com.shop.admin.dto;

import java.util.Date;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

public class CustomRememberMeToken {

	private String series;
	private String username;
	private String tokenValue;
	private Date date;
		
	public CustomRememberMeToken(PersistentRememberMeToken prmToken) {

		this.series = prmToken.getSeries();
		this.username = prmToken.getUsername();
		this.tokenValue = prmToken.getTokenValue();		
	}
	
	public CustomRememberMeToken() {
		
	}
	
	public CustomRememberMeToken(String series, String tokenValue) {
		this.series = series;
		this.tokenValue = tokenValue;
	}
	
	
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
	
}
