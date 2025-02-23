package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.Users;
import com.repository.UserRepository;
import com.util.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private final UserRepository userRepository;
	
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Users users = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
		
		
		
		return new CustomUserDetails(users);
	}

}
