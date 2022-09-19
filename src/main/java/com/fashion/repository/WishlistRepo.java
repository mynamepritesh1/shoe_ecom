package com.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashion.entities.User;
import com.fashion.entities.WishList;

public interface WishlistRepo extends JpaRepository<WishList, Integer> {
	
	
	 List<WishList> findAllByUserOrderByCreatedDateDesc(User user);

}
