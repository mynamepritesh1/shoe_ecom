package com.fashion.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashion.dto.ResponseDto;
import com.fashion.dto.SignInDto;
import com.fashion.dto.SignInReponseDto;
import com.fashion.dto.SignUpDto;
import com.fashion.entities.Authentication;
import com.fashion.entities.User;
import com.fashion.exception.AuthenticationFailException;
import com.fashion.exception.CustomException;
import com.fashion.repository.UserRepo;
import com.fashion.service.AuthenticationService;
import com.fashion.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	AuthenticationService authenticationService;

//	@Autowired
//	SignUpDto signUpDto;

	@Override
	public ResponseDto signUp(SignUpDto signUpDto) {

		// check if user is already present or not
		if (Objects.nonNull(userRepo.findByEmail(signUpDto.getEmail()))) {
			
		// we have an user
		throw new CustomException("user already present");
		}

	// hash the password

	String encryptedpassword = signUpDto.getPassword();

	try
	{
		encryptedpassword = hashPassword(signUpDto.getPassword());
	}catch(
	NoSuchAlgorithmException e)
	{
		e.printStackTrace();
	}

	User user = new User(signUpDto.getFirstName(),signUpDto.getLastName(),signUpDto.getEmail(),encryptedpassword);

	userRepo.save(user);

	// save the user

	// create the token

	final Authentication authentication = new Authentication(user);

	authenticationService.saveConfirmationToken(authentication);

	ResponseDto responseDto = new ResponseDto("success", "user created succesfully");
	return responseDto;
}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}

	@Override
	public SignInReponseDto signIn(SignInDto signInDto) {
		
		//find user by email
		
	User user=	userRepo.findByEmail(signInDto.getEmail());
	if (Objects.isNull(user)) {
        throw new AuthenticationFailException("user is not valid");
    }

    // hash the password

    try {
        if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
            throw new AuthenticationFailException("wrong password");
        }
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }

    // compare the password in DB

    // if password match

    Authentication token = authenticationService.getToken(user);

    // retrive the token

    if (Objects.isNull(token)) {
        throw new CustomException("token is not present");
    }

    return new SignInReponseDto("sucess", token.getToken());	}
}

	
