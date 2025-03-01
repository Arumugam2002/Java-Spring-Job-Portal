package com.services;

import java.security.Security;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.entity.JobSeekerProfile;
import com.entity.RecruiterProfile;
import com.entity.Users;
import com.repository.JobSeekerProfileRepository;
import com.repository.RecruiterProfileRepository;
import com.repository.UserRepository;

@Service
public class UsersService {

	@Autowired
	public final UserRepository userRepository;
	public final JobSeekerProfileRepository jobSeekerProfileRepository;
	public final RecruiterProfileRepository recruiterProfileRepository;
	public final PasswordEncoder passwordEncoder;

	public UsersService(UserRepository userRepository, JobSeekerProfileRepository jobSeekerProfileRepository,
			RecruiterProfileRepository recruiterProfileRepository, PasswordEncoder passwordEncoder) {

		this.userRepository = userRepository;
		this.jobSeekerProfileRepository = jobSeekerProfileRepository;
		this.recruiterProfileRepository = recruiterProfileRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Users addNewUsers(Users users) {
		users.setActive(true);
		users.setRegistrationDate(new Date(System.currentTimeMillis()));

		users.setPassword(passwordEncoder.encode(users.getPassword()));

		Users savedUsers = userRepository.save(users);
		int userTypeId = users.getUserTypeId().getUserTypeId();
		if (userTypeId == 1) {
			recruiterProfileRepository.save(new RecruiterProfile(savedUsers));
		} else {
			jobSeekerProfileRepository.save(new JobSeekerProfile(savedUsers));
		}

		return savedUsers;
	}

	public Optional<Users> getUsersByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Object getCurrentUserProfile() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			Users users = userRepository.findByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("Could not found user"));

			int userId = users.getUserId();
			
			if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter")))
			{
				RecruiterProfile recruiterProfile = recruiterProfileRepository.findById(userId).orElse(new RecruiterProfile());
				
			}
			else 
			{
				JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepository.findById(userId).orElse(new JobSeekerProfile());
				
			}

		}
		return null;
	}

}
