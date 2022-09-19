package com.fashion.service;

import java.util.List;

import com.fashion.dto.ProductDto;
import com.fashion.entities.User;
import com.fashion.entities.WishList;

public interface WishListService {
	
	//create wishList
	 public WishList createwishList(WishList wishList);
	 


    //update wishList
     public WishList updatewishList(WishList wishList,Integer wishListId);
    	 
    	
     //delete wishList
     public void deletewishList(Integer wishListId);
     
     
     //get wishList for user
     public List<ProductDto> getWishListForUser(User user); 
     
     
     //get a single wishList for user
     public WishList getwishList(Integer wishListId);
     
     
     
     
     }
     
     
     
    