package com.fashion.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private int categoryId;

	@NotBlank
	@Size(min = 3, max = 10, message = "Please enter charecter in between given range")
	private String categoryName;

	@NotBlank
	@Size(min = 3, max = 100, message = "Please enter charecter in between given range")
	private String description;

	@NotBlank
	private String imageUrl;
	
	

}
