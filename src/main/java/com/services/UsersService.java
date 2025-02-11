package com.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public UsersService(UserRepository userRepository, JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository) {
		
		this.userRepository = userRepository;
		this.jobSeekerProfileRepository = jobSeekerProfileRepository;
		this.recruiterProfileRepository = recruiterProfileRepository;
	}
	
	public Users addNewUsers(Users users)
	{
		users.setActive(true);
		users.setRegistrationDate(new Date(System.currentTimeMillis()));
		Users savedUsers = userRepository.save(users);
		int userTypeId = users.getUserTypeId().getUserTypeId();
		if(userTypeId == 1)
		{
			recruiterProfileRepository.save(new RecruiterProfile(savedUsers));
		}
		else {
			jobSeekerProfileRepository.save(new JobSeekerProfile(savedUsers));
		}
		
		
		return savedUsers;
	}
	
	public Optional<Users> getUsersByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
	
	
}
