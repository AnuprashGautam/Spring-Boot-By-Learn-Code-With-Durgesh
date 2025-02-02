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
	@GetMapping("/service")
	public String serviceHandler(Model model)
	{
		// Sending dynamic data to the service and then to footer.
		
		model.addAttribute("title","This is title from controller.");
		// model.addAttribute("subTitle","This is sub title form controller.");
		model.addAttribute("subTitle",LocalDateTime.now().toString());
		
		return "service";
	}
}