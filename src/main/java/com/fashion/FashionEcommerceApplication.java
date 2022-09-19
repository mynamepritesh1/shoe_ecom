package com.fashion;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FashionEcommerceApplication{
	
	


	public static void main(String[] args) {
		SpringApplication.run(FashionEcommerceApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelapper() {
		return new ModelMapper();
	}
	
	


}
