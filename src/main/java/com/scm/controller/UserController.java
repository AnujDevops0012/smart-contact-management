package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
// localhost:8080/user/index
	@RequestMapping("/index")
	public String dashboard()
	{
		return "normal/user_dashboard";
	}
	// localhost:8080/user/login
//	@RequestMapping("/login")
//	public String dashboard1()
//	{
//		return "normal/user_dashboard";
//	}
}
