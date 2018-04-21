package com.springjpa.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springjpa.model.Customer;
import com.springjpa.repo.CustomerRepository;

@Controller
public class AppController {
	@Autowired
	CustomerRepository repository;
	
	public Customer cust = new Customer(); 

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title","CRUD Example");
		return "index";
	}

	@RequestMapping("/add")
	public String add(Customer cust){
		// save a single Customer
		repository.save(new Customer(cust.getFirstName(), cust.getLastName()));
		
		return "index";
	}
	
	/***
	 * @author 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	@RequestMapping("/add2")
	@Deprecated
	public String add2(ModelMap modal,@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
		// save a single Customer
		modal.addAttribute("title","CRUD Example");
		
		repository.save(new Customer(firstName, lastName));
		
		return "index";
	}
	
}

