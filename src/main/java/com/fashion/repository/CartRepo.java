package com.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashion.entities.Cart;
import com.fashion.entities.User;

public interface CartRepo  extends JpaRepository<Cart, Integer>{
	
	List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
