//package com.shop.main.security.core;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//public class CustomSpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// TODO Auto-generated method stub
//		super.configure(http);
//		http
//			.headers()
//				.frameOptions()
//				.sameOrigin()
//			.and()
//			.csrf()
//				.ignoringAntMatchers("/admin/**")
//		
//	}
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web
//	    	.ignoring()
//	    	.antMatchers("/resources/**"); // #3
//	}
//	
//	@Autowired
//	AuthenticationProvider authenticationProvider;
//	
//	@Autowired
//	UserDetailsService userService;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)
//			throws Exception {
//		auth
//			.authenticationProvider(authenticationProvider)
//			.userDetailsService(userService);
//	}
//
//	
//}
