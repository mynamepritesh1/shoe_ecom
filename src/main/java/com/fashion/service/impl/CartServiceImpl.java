package com.fashion.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashion.dto.AddToCartDto;
import com.fashion.dto.CartDto;
import com.fashion.dto.CartItemDto;
import com.fashion.entities.Cart;
import com.fashion.entities.Product;
import com.fashion.entities.User;
import com.fashion.exception.CustomException;
import com.fashion.repository.CartRepo;
import com.fashion.service.CartService;
import com.fashion.service.ProductService;



@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepo cartRepo;
	 
	
	@Autowired
	private ProductService productServive;

	
	//add
	@Override
	public void addToCart(AddToCartDto addToCartDto, User user) {
        
         Product product = productServive.findById(addToCartDto.getProductId());
         
         Cart cart = new Cart();
         cart.setProduct(product);
         cart.setQuantity(addToCartDto.getQuantity());
         cart.setUser(user);
         cart.setCreatedDate(new Date());
         
         // save the cart to database
         cartRepo.save(cart);
         
	}

	
	//delete
	@Override
	public void deleteCartItem(Integer cartItemId, User user) {
        // the item id belongs to user

        Optional<Cart> optionalCart = cartRepo.findById(cartItemId);

        if (optionalCart.isEmpty()) {
            throw new CustomException("cart item id is invalid: " + cartItemId);
        }

        Cart cart = optionalCart.get();

        if (cart.getUser() != user) {
            throw  new CustomException("cart item does not belong to user: " +cartItemId);
        }

        cartRepo.delete(cart);


	}

	
	//list all items in cart
	@Override
	public CartDto listCartItems(User user) {
		 List<Cart> cartList = cartRepo.findAllByUserOrderByCreatedDateDesc(user);

	        List<CartItemDto> cartItems = new ArrayList<>();
	        double totalCost = 0;
	        for (Cart cart: cartList) {
	            CartItemDto cartItemDto = new CartItemDto(cart);
	            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
	            cartItems.add(cartItemDto);
	        }

	        CartDto cartDto = new CartDto();
	        cartDto.setTotalCost(totalCost);
	        cartDto.setCartItems(cartItems);
	        return  cartDto;
	    }

	}


