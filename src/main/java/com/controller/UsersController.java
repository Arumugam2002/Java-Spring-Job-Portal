package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.entity.UsersType;
import com.entity.Users;
import com.services.UserTypeService;
import com.services.UsersService;

import jakarta.validation.Valid;

@Controller
public class UsersController {

	@Autowired
	private final UserTypeService userTypeService;
	private final UsersService usersService;

	
	public UsersController(UserTypeService userTypeService, UsersService usersService) {
		
		this.userTypeService = userTypeService;
		this.usersService = usersService;
	}

	@GetMapping("/register")
	public String register(Model model)
	{
		List<UsersType> usersTypes = userTypeService.getAll();
		model.addAttribute("getAllTypes",usersTypes);
		model.addAttribute("user", new Users());
		
		return "register";
		
	}
	
	@PostMapping("/register/new")
	public String userRegistration(@Valid Users user, Model model)
	{
		System.out.println("Users:- " + user);
		
		Optional<Users> allUsers =  usersService.getUsersByEmail(user.getEmail());
		
		if(allUsers.isPresent())
		{
			model.addAttribute("error", "Email is being already registered");
			List<UsersType> usersTypes = userTypeService.getAll();
			model.addAttribute("getAllTypes",usersTypes);
			model.addAttribute("user", new Users());
			
			return "register";
		}
		usersService.addNewUsers(user);
		return "dashboard";
	}
	
}
