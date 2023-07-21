package com.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scm.entities.User;
import com.scm.helper.Message;
import com.scm.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TestController {
	@Autowired
	private UserRepository userRepository;

//	@GetMapping("/user")
//	public String createuser(@RequestBody User user)
//	{
//		
//		userRepository.save(user);
//		return "This is a test function";
//	}
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
//	@GetMapping("/greeting")
//	public String greeting1() {
//
//		return "index";
//	}
	@RequestMapping ("/")
	public String homePage(Model model )
	{
		model.addAttribute("title","Home-Smart Contect Manager");
		return "home";
	}
	@RequestMapping("/signup")
	public String signup( Model model)
	{
		model.addAttribute("title","Registration-Smart Contect Manager");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult result1,@RequestParam(value = "agreement",defaultValue = "false" ) boolean agreemant,Model model, HttpSession session) throws Exception
	{
		try {
		if(!agreemant)
		{
			System.out.println("You have not agreed the terms and conditiond");
			throw new Exception("You have not agreed the terms and conditiond");
		}
		if(result1.hasErrors())
		{
			System.out.println("ERROR "+result1.toString());
			model.addAttribute("user "+user);
			return "signup";
		}
		user.setRole("ROLE_USER");
		user.setEnable(true);
		
		System.out.println("Agreement "+agreemant);
		System.out.println("User "+ user);
		
		User result=userRepository.save(user);
		
		model.addAttribute("user", result);
		session.setAttribute("message", new Message("Successfully Register !!","alert-success"));
		
		return "signup";
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message("Something Went Wrong!!"+e.getMessage(),"alert-danger"));
			return "signup";
		}
		
	}
}
