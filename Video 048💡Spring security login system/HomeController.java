package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("title", "About - Smart Contact Manager");
		return "about";
	}
	
	@RequestMapping("/signup")
	public String signUp(Model model,HttpSession session)
	{
		session.removeAttribute("message");
		model.addAttribute("title", "Sign up - Smart Contact Manager");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	// Handler for registering user.
	@PostMapping("/do_register")
	public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult bindingResult, @RequestParam(value="agreement",defaultValue="false") boolean agreement,Model model, HttpSession session)
	{
		session.removeAttribute("message");
		
		try {
			if(!agreement) {
				System.out.println("You have not agreed the terms and conditions.");
				throw new Exception("You have not agreed the terms and conditions.");
			}
			
			if(bindingResult.hasErrors()) {
				System.out.println("Error: "+bindingResult.toString());
				model.addAttribute("user",user);
				return "signup";
			}
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			System.out.println("Agreement"+agreement);
			System.out.println("User "+user);
			
			this.userRepository.save(user);
			
			model.addAttribute("user",new User());
			session.setAttribute("message",new Message("Successfully registered !!!","alert-success"));
			return "signup";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message",new Message("Something went wrong !!!","alert-danger"));
			
			return "signup";
		}
	}
	
}
