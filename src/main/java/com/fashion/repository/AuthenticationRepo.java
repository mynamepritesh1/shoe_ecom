package com.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashion.entities.Authentication;
import com.fashion.entities.User;

public interface AuthenticationRepo extends JpaRepository<Authentication, Integer> {
	
	Authentication findByUser(User user);

	Authentication findByToken(String token);

}
