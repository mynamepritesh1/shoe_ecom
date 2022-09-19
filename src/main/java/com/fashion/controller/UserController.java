package com.fashion.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.dto.ResponseDto;
import com.fashion.dto.SignInDto;
import com.fashion.dto.SignInReponseDto;
import com.fashion.dto.SignUpDto;
import com.fashion.service.UserService;

@RestController
@RequestMapping("/ecom/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	//two apis
	
	
	//sign up
	@PostMapping("/signup")
	public ResponseDto signup(@Valid @RequestBody SignUpDto signUpDto) {
		
		
		return userService.signUp(signUpDto);
		
	}
	
	
	//sign in
	
    @PostMapping("/signin")
    public SignInReponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }

	
	

}
