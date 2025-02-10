package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Users;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByEmail(String email);
}
