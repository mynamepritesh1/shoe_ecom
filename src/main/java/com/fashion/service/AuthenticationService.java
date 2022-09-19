package com.fashion.service;

import com.fashion.entities.Authentication;
import com.fashion.entities.User;

public interface AuthenticationService {
	
	
	//save confirmation token
	public void saveConfirmationToken(Authentication authentication);
	
	
	//get token
	public Authentication getToken(User user);
	
	//get user
	public User getUser(String token);
	
	//authenticate the token
    public void authenticate(String token);
    
    }
	
	
	
	


