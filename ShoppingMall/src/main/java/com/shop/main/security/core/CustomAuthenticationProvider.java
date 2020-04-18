package com.shop.main.security.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.shop.main.security.role.CustomUser;

@Repository("customProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
    private CustomUserService userService;
	
	@Autowired
	BCryptPasswordEncoder encodePassword;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//입력된 아이디와 비밀번호.
		String u_account = (String) authentication.getPrincipal();
		String u_password = (String) authentication.getCredentials();
				
		CustomUser user = (CustomUser)userService.loadUserByUsername(u_account);
		//password비교
		if (!encodePassword.matches(u_password, user.getPassword())) {
			throw new BadCredentialsException(u_account);
		}
		//enable 검증
		if(!user.isEnabled()) {
            throw new DisabledException(u_account);
        }
		//SecurityContext에 저장.
		return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
