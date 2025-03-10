package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.entity.UsersType;
import com.entity.Users;
import com.services.UserTypeService;
import com.services.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		 return "redirect:/dashboard/";
	}
	
	@GetMapping("/login")
	public String getLogin()
	{
		return "login";
	}
	
	@GetMapping("/logout")
	public String getLogout(HttpServletRequest request, HttpServletResponse response)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication!=null)
		{
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		return "redirect:/";
	}
	
}
