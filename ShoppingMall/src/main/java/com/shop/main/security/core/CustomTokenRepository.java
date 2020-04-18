package com.shop.main.security.core;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import com.shop.admin.dto.CustomRememberMeToken;
import com.shop.dao.TokenDAO;

@Repository("customTokenRepository")
public class CustomTokenRepository implements PersistentTokenRepository {

	@Autowired
	TokenDAO dao;
	
	//Remember me Token 서비스 구현부
	
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		dao.createNewToken(new CustomRememberMeToken(token));
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		dao.updateNewToken(new CustomRememberMeToken(series, tokenValue));
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		CustomRememberMeToken token = dao.getTokenForSeries(seriesId);
		return new PersistentRememberMeToken(token.getUsername(),
				token.getSeries(), token.getTokenValue(), token.getDate());
	}

	@Override
	public void removeUserTokens(final String username) {
		dao.removeUserToken(username);
	}
	
}
