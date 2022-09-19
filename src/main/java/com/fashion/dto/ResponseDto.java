package com.fashion.dto;

import lombok.Data;

@Data
public class ResponseDto {

	private String message;
	
	private String status;
	
	public ResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
	
}
