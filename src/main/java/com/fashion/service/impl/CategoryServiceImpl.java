package com.fashion.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashion.dto.CategoryDto;
import com.fashion.entities.Category;
import com.fashion.exception.ResourceNotFoundException;
import com.fashion.repository.CategoryRepo;
import com.fashion.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
			
	public Category dtoToCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
	
		return category;
		
	}
	public CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		
	return categoryDto;
	}


	@Override
	public CategoryDto createcategoryDto(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat = this.categoryRepo.save(cat);
		
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}
	@Override
	public CategoryDto updatecategoryDto(CategoryDto categoryDto, Integer categoryId) {
		Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()->
		new ResourceNotFoundException("Category", "CategoryId",categoryId));
		cat.setCategoryName(categoryDto.getCategoryName());
		cat.setImageUrl(categoryDto.getImageUrl());
		cat.setDescription(categoryDto.getDescription());
		Category updatedcat = this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedcat,CategoryDto.class);
	}

	@Override
	public void deletecategoryDto(Integer categoryId) {
		Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()->
		new ResourceNotFoundException("Category", "CategoryId",categoryId));
		this.categoryRepo.delete(cat);
		
	}
		
		//get category by id
	@Override
	public CategoryDto getcategoryDto(Integer categoryId) {
		Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()->
		new ResourceNotFoundException("Category", "CategoryId",categoryId));
		
			return this.modelMapper.map(cat, CategoryDto.class);
		}

	//get all categories
	
	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categeries = this.categoryRepo.findAll();
		List<CategoryDto> categoryDtos = 
				categeries.stream().map(category->this.categoryToDto(category))
				.collect(Collectors.toList());

		return categoryDtos;
	}
}
