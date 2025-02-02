package com.practice.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MyController {	
	@GetMapping("/condition")
	public String conditionHandler(Model model)
	{
		System.out.println("Executing the condition handler.");
		
		model.addAttribute("isActive",false);
		model.addAttribute("gender","Female");
		
		List<Integer> list = List.of();
		
		model.addAttribute("myList",list);
		
		return "condition";
	}
}