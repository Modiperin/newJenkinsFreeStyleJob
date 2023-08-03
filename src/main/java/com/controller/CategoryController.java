package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/category")
@Controller
public class CategoryController {
//	category
	@GetMapping
	public String newCategory()
	{
		System.out.println("NewCategory");
		return "NewCategory";
	}

	//   category/all
	@GetMapping("/all")
	public String allUsers()
	{
		
		return "redirect:/showusers";
	}
}
