package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Users;
import com.example.demo.service.TaskService;
import com.example.demo.service.UsersService;

@Controller
public class UserController {
	
	
	
	@Autowired
	public UsersService service;
	
	@PostMapping("/register")
	 public String addUser(@ModelAttribute Users user) {
		
		System.out.println("Users::"+user);
		
		boolean userStatus=service.emailExixts(user.getEmail());
		System.out.println("Users::"+userStatus);
		if(userStatus==true) {
     boolean isUserSaved = service.addUser(user);
     if(isUserSaved == true) {
    	 System.out.println("user added success");
     } else {
    	 System.out.println("Error");
     }
     
		}else
			System.out.println("exists");
		 return "login"; 
	 }
	
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		if(service.validateUser(email,password)==true) {
			String role=service.getRole(email);
			if(role.equals("admin")) {
				return "adminHome";
			}else {
				return "customerHome";
			}
		}else {
     return  "login";}
}
	

}