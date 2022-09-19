package com.fashion.dto;

import lombok.Data;

@Data
public class SignInReponseDto {
	
	private String status;
	
	private String token;
	
	public SignInReponseDto(String status, String token) {
        this.status = status;
        this.token = token;
    }

}
