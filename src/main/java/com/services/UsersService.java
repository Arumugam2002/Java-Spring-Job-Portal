package com.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Users;
import com.repository.UserRepository;

@Service
public class UsersService {

	@Autowired
	public final UserRepository userRepository;

	public UsersService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	public Users addNewUsers(Users users)
	{
		users.setActive(true);
		users.setRegistrationDate(new Date(System.currentTimeMillis()));
		return userRepository.save(users);
	}
	
	public Optional<Users> getUsersByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
	
	
}
