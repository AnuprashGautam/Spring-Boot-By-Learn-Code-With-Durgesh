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
	@RequestMapping(value="/about", method=RequestMethod.GET)
	public String about(Model model)
	{
		System.out.println("Going to the about page...");
		
		// Sending data to the template.
		
		model.addAttribute("name","Anuprash Gautam");
		model.addAttribute("currentDate", new Date().toLocaleString());
		
		return "about";
	}
	
	@GetMapping("/example-loop")
	public String iterateHandler(Model model)
	{
		//Sending the object, to which we want to traverse with the help of loop.
		List<String> names = List.of("Ankit","Laxmi","John","Karan");
		model.addAttribute("list",names);
		
		return "iterate";
	}
	
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