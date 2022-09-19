package com.fashion.service;

import java.util.List;

import com.fashion.dto.CategoryDto;


public interface CategoryService {
	
	
	
	    //create
		public CategoryDto createcategoryDto(CategoryDto categoryDto);
		
		
		//update
		public CategoryDto updatecategoryDto(CategoryDto categoryDto,Integer categoryId);
		
		
		//delete
		public void deletecategoryDto(Integer categoryId);
		
		
		//get
		public CategoryDto getcategoryDto(Integer categoryId);
		
		
		
		//getAll
		List<CategoryDto> getCategories();
			
}

