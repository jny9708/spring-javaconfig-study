package com.study.web.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
	private String username;
	private String password;
	private String authority;
	private boolean enabled;
	private String name;
}
