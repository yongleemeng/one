package com.springjpa.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TestUtils;
import com.springjpa.model.Student;
import com.springjpa.repo.StudentRepository;

@RestController
public class MyController {
	@Autowired
	StudentRepository repository;
	@Autowired
	EntityManager em;

	@RequestMapping("/invalid_ip")
	public String displayAllInvalidIP(){
		String result = TestUtils.constructInvalidIP("D:/ip.txt");
		
		
		return result;
	}
	

	@RequestMapping("/results")
	public String fetchResults(){
		long startTime = System.currentTimeMillis();
		List<Student> list = em.createNativeQuery("SELECT * FROM student ORDER BY gpa desc,name asc,id asc",Student.class).getResultList();
		StringBuilder sb = new StringBuilder();
		
		for(Student s: list){
			sb.append(s.toString());
			sb.append("<br/>"); 
		}
		
		long endTime = System.currentTimeMillis();
		sb.append(TestUtils.displayTimeConsumed(startTime, endTime));
		
		return sb.toString();
	}
	

	/***
	 * @author leemeng 20180421
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	@RequestMapping("/add_student")
	@Deprecated
	public String addStudent(ModelMap modal,@RequestParam("name") String name, @RequestParam("gpa") BigDecimal gpa){
		// save a single Customer
		modal.addAttribute("title","CRUD Example");
		
		repository.save(new Student(name, gpa));
		
		return fetchResults();
	}
	
}

