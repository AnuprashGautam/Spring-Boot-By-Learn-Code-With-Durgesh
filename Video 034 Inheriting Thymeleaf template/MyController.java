package com.practice.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MyController {
	@GetMapping("/newAbout")
	public String newAboutHandler()
	{		
		return "newAbout";
	}
	
	@GetMapping("/contact")
	public String contactHandler()
	{		
		return "contact";
	}
	
}