package com.fashion.service;

import com.fashion.dto.ResponseDto;
import com.fashion.dto.SignInDto;
import com.fashion.dto.SignInReponseDto;
import com.fashion.dto.SignUpDto;


public interface UserService {

	ResponseDto signUp(SignUpDto signUpDto);

	SignInReponseDto signIn(SignInDto signInDto);
	
	

}
