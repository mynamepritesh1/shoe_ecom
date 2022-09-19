package com.fashion.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashion.dto.ProductDto;
import com.fashion.entities.User;
import com.fashion.entities.WishList;
import com.fashion.exception.ResourceNotFoundException;
import com.fashion.repository.WishlistRepo;
import com.fashion.service.ProductService;
import com.fashion.service.WishListService;


@Service
public class WishListServiceImpl implements WishListService {
	
	@Autowired
	private WishlistRepo wishlistRepo;
	
	
	@Autowired
	private ProductService productService;
	
	
	//create

	@Override
	public WishList createwishList(WishList wishList) {
		
		
				return this.wishlistRepo.save(wishList);
	}

	
	//update
	
	@Override
	public WishList updatewishList(WishList wishList, Integer wishListId) {
		WishList wish = this.wishlistRepo.findById(wishListId).orElseThrow(()->
		new ResourceNotFoundException("Wishlist", "wishListId", wishListId));
		
		wish.setId(wishList.getId());
		wish.setProduct(wishList.getProduct());
		wish.setUser(wishList.getUser());
		wish.setCreatedDate(wishList.getCreatedDate());
		
		
		
		
				return this.wishlistRepo.save(wish);
	}

	//delete
	 
	@Override
	public void deletewishList(Integer wishListId) {
		WishList wish = this.wishlistRepo.findById(wishListId).orElseThrow(()->
		new ResourceNotFoundException("Wishlist", "wishListId", wishListId));
		
		this.wishlistRepo.delete(wish);
		
	}

	//get all list
	
	@Override
	public List<ProductDto> getWishListForUser(User user) {
		final List<WishList> wishLists = wishlistRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for (WishList wishList: wishLists) {
            productDtos.add(productService.getproductDto(wishList.getProduct()));
        }

        return productDtos;
	}
		

	//get single list

	@Override
	public WishList getwishList(Integer wishListId) {
		WishList wish = this.wishlistRepo.findById(wishListId).orElseThrow(()->
		new ResourceNotFoundException("Wishlist", "wishListId", wishListId));
		
		return wish;
		
		
	}
		
}
