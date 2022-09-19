package com.fashion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.dto.ProductDto;
import com.fashion.entities.Product;
import com.fashion.entities.User;
import com.fashion.entities.WishList;
import com.fashion.helper.ApiResponse;
import com.fashion.service.AuthenticationService;
import com.fashion.service.WishListService;

@RestController
@RequestMapping("/ecom/wish")
public class WishListController {
	
	
	@Autowired
	private WishListService wishListService;
	
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	
	//create wishList handler
	@PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
                                                     @RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);


        // find the user

        User user = authenticationService.getUser(token);

        // save the item in wishlist

        WishList wishList = new WishList(user, product);

        wishListService.createwishList(wishList);

        ApiResponse apiResponse = new ApiResponse("Added to wishlist", true);
        return  new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }
	
	//get all wishList handler
	
	 @GetMapping("/{token}")
	    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {

	        // authenticate the token
	        authenticationService.authenticate(token);


	        // find the user

	        User user = authenticationService.getUser(token);

	        List<ProductDto> productDtos = wishListService.getWishListForUser(user);

	        return new ResponseEntity<>(productDtos, HttpStatus.OK);

	    }
	 
	 
	 //update wishList
	 
//	 @PutMapping("/{wishListId}")
//	 public ResponseEntity<WishList> updateWishList
//	 (@RequestBody Product product,@PathVariable Integer wishListId ,@RequestParam("token") String token){
//		
//		 // authenticate the token
//	        authenticationService.authenticate(token);
//
//
//	        // find the user
//
//	        User user = authenticationService.getUser(token);
//	        
//	     // save the item in wishlist
//
//	        WishList wishList = new WishList(user, product);
//
//	        WishList updatewishList = wishListService.updatewishList(wishList, wishListId);
//
//	   
//	        return  new ResponseEntity<WishList>(updatewishList,HttpStatus.ACCEPTED);
//
//
//	 }
//	 
//	 
	 @DeleteMapping("/{wishListId}")
	 public ResponseEntity<ApiResponse> deleteWishList(@PathVariable Integer wishListId,@RequestParam("token") String token){
		 this.wishListService.deletewishList(wishListId);
		 
		 return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully", true), HttpStatus.OK);

 
} 
	 
	 
	 
	 


}
