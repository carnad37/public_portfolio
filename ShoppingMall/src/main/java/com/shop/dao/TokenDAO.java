package com.shop.dao;

import com.shop.admin.dto.CustomRememberMeToken;

//Remeber-me Token DAO

public interface TokenDAO {
	
	public void createNewToken(CustomRememberMeToken token);
	
	public void updateNewToken(CustomRememberMeToken token);
	
	public CustomRememberMeToken getTokenForSeries(String seriesId);
	
	public void removeUserToken(String username);	
	
}
