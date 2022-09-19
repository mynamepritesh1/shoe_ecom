package com.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashion.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	 User findByEmail(String email);

	

}
