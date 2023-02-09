package com.intend.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.intend.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto user);
}
