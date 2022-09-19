package com.fashion.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDto {
	
	

	private Integer id;

    
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;
    
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public UserDto(String firstName,  String lastName, String email,
   		 String password) {
   		super();
   		
   		this.firstName = firstName;
   		this.lastName = lastName;
   		this.email = email;
   		this.password = password;
   	}


}
