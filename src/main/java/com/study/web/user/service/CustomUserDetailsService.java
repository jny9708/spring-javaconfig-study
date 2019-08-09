package com.study.web.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.web.user.model.CustomUserDetails;
import com.study.web.user.model.UserAuthDAO;
import com.study.web.user.model.UserVO;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserAuthDAO userAuthDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUserDetails user = userAuthDAO.getUserById(username);
		if(user==null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}

	
	public int saveUser(UserVO userVO) throws Exception{
		userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
		userVO.setAuthority("ROLE_USER");
		userVO.setEnabled(true);
		return userAuthDAO.insertUser(userVO);
	}
	
}

