package com.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.RecruiterProfile;
import com.repository.RecruiterProfileRepository;

@Service
public class RecruiterProfileService {

	@Autowired
	private final RecruiterProfileRepository recruiterProfileRepository;

	@Autowired
	public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository) {
		
		this.recruiterProfileRepository = recruiterProfileRepository;
	}
	
	public Optional<RecruiterProfile> getOne(Integer id)
	{
		return recruiterProfileRepository.findById(id);
	}
}
