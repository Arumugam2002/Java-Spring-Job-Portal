package com.controller;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.RecruiterProfile;
import com.entity.Users;
import com.repository.UserRepository;
import com.services.RecruiterProfileService;

@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {

	
	
	private final UserRepository userRepository;
	
	private final RecruiterProfileService recruiterProfileService;
	
	
	public RecruiterProfileController(UserRepository userRepository, RecruiterProfileService recruiterProfileService) {
		
		this.userRepository = userRepository;
		this.recruiterProfileService = recruiterProfileService;
	}




	@GetMapping("/")
	public String recruiterProfile(Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(!(authentication instanceof AnonymousAuthenticationToken))
		{
			String currentUsername = authentication.getName();
			
		Users users =	userRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not found username"));
			
		Optional<RecruiterProfile> recruiterProfile = recruiterProfileService.getOne(users.getUserId());
		
		if(!recruiterProfile.isEmpty())
		{
			model.addAttribute("profile", recruiterProfile.get());
		}
		
		}
		
		return "recruiter_profile";
	}
}
