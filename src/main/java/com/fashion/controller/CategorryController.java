package com.fashion.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.dto.CategoryDto;
import com.fashion.helper.ApiResponse;
import com.fashion.service.CategoryService;

@RestController
@RequestMapping("/ecom/cat")
public class CategorryController {

	@Autowired
	private CategoryService categoryService;

//	@Autowired
//	CategoryRepo categoryRepo;	

	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		// adding data in data base

		CategoryDto createCategory = this.categoryService.createcategoryDto(categoryDto);

		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}
     //get all category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getcategories() {

		return ResponseEntity.ok(this.categoryService.getCategories());

	}

	// get single category

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {

		return ResponseEntity.ok(this.categoryService.getcategoryDto(categoryId));

	}

	// update handler
	@PutMapping("{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId) {

		CategoryDto updatedCategory = this.categoryService.updatecategoryDto(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}

	// delete handler
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {

		this.categoryService.deletecategoryDto(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully", true), HttpStatus.OK);

	}
	
	
	//
	

}
