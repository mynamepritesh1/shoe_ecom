package com.fashion.service;

import com.fashion.dto.AddToCartDto;
import com.fashion.dto.CartDto;
import com.fashion.entities.User;

public interface CartService {
	
	//add
	public void addToCart(AddToCartDto addToCartDto, User user) ;
	
	
	//delete
	public void deleteCartItem(Integer cartItemId, User user);
	
	
	//get all
	public CartDto listCartItems(User user) ;
	
		
	

}
