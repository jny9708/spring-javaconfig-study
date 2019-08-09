package com.study.web.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.web.user.model.UserVO;
import com.study.web.user.service.CustomUserDetailsService;

@Controller
public class UserContoller {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	
	@RequestMapping(value="/login")
    public String loginPage() throws Exception {
        return "login";
    }
	
	@RequestMapping("/signUpPage")
	public String signUpPage() throws Exception{
		System.out.println("dddd뭐야!");
		return "user/signUpPage";
	}
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public String saveUser(UserVO userVO, RedirectAttributes rttr) throws Exception{
		System.out.println("dddd");
		customUserDetailsService.saveUser(userVO);
		return "redirect:/login";
	}
}
