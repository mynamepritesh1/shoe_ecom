package com.fashion.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashion.entities.Authentication;
import com.fashion.entities.User;
import com.fashion.exception.AuthenticationFailException;
import com.fashion.repository.AuthenticationRepo;
import com.fashion.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	private AuthenticationRepo authenticationRepo;

	@Override
	public void saveConfirmationToken(Authentication authentication) {
 
		
		this.authenticationRepo.save(authentication);
		
		
			
	}

	@Override
	public Authentication getToken(User user) {
		
		return authenticationRepo.findByUser(user);
	}

	@Override
	public User getUser(String token) {
		 final Authentication authenticationDto = authenticationRepo.findByToken(token);
	        if(Objects.isNull(authenticationDto)) {
	            return null;
	        }
	        // authenticationToken is not null
	        return authenticationDto.getUser();

	}

	@Override
	public void authenticate(String token) throws AuthenticationFailException {
		 // null check
        if(Objects.isNull(token)) {
            // throw an exception
            throw new AuthenticationFailException("token not present");
        }
        if(Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("token not valid");
        }
		
		
	}

	
}
