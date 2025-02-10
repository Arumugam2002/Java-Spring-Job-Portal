package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.UsersType;
import com.repository.UsersTypeRepository;

@Service
public class UserTypeService {
	
	@Autowired
	public final UsersTypeRepository usersTypeRepository;


	public UserTypeService(UsersTypeRepository usersTypeRepository) {
		
		this.usersTypeRepository = usersTypeRepository;
	}
	
	public List<UsersType> getAll()
	{
		return usersTypeRepository.findAll();
	}
	
	
	
}
