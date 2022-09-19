package com.fashion.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.dto.ProductDto;
import com.fashion.entities.Category;
import com.fashion.helper.ApiResponse;
import com.fashion.repository.CategoryRepo;
import com.fashion.service.ProductService;

@RestController
@RequestMapping("/ecom/prod")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	CategoryRepo categoryRepo;

	// create product handler

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
		Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
		if (!optionalCategory.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("category does not exists", false),
					HttpStatus.BAD_REQUEST);
		}
		productService.createProduct(productDto, optionalCategory.get());
		return new ResponseEntity<ApiResponse>(new ApiResponse("product has been added", true), HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<ProductDto>> getProducts() {
		List<ProductDto> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	// create an api to edit the product

	@PostMapping("/update/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId,
			@RequestBody ProductDto productDto) throws Exception {
		Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
		if (!optionalCategory.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("category does not exists", false),
					HttpStatus.BAD_REQUEST);
		}
		productService.updateProduct(productDto, productId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("product has been updated", true), HttpStatus.OK);
	}

}
