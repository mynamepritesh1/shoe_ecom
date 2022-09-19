package com.fashion.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignUpDto {
	
	private String firstName;
    private String lastName;
    private String email;
    private String password;
	

}
