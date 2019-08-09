package com.study.web.user.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userAuthDAO")
public class UserAuthDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public CustomUserDetails getUserById(String username) {
		return sqlSession.selectOne("com.study.web.user.selecUserById",username);
	}
	
	public int insertUser(UserVO userVO) {
		return sqlSession.insert("com.study.web.user.insertUser", userVO);
	}
	
}
