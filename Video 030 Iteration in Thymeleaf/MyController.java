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
	@GetMapping("/example-loop")
	public String iterateHandler(Model model)
	{
		//Sending the object, to which we want to traverse with the help of loop.
		List<String> names = List.of("Ankit","Laxmi","John","Karan");
		model.addAttribute("list",names);
		
		return "iterate";
	}
	
}