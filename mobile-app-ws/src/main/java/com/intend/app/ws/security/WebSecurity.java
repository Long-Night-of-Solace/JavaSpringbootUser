package com.intend.app.ws.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import com.intend.app.ws.service.UserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurity {
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoader;
		public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
			this.userDetailsService = userDetailsService;
			this.bCryptPasswordEncoader = new BCryptPasswordEncoder();
			}
		
		@SuppressWarnings("deprecation")
		@Bean
		protected DefaultSecurityFilterChain configure(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests().requestMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
			.permitAll()
			.anyRequest().authenticated().and();//.addFilter(new AutheticationFilter(authenticationManager()));
			
			return http.build();
		}
		
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoader);
		}
}
