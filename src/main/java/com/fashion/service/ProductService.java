package com.fashion.service;

import java.util.List;

import com.fashion.dto.ProductDto;
import com.fashion.entities.Category;
import com.fashion.entities.Product;
import com.fashion.exception.ProductNotExistsException;

public interface ProductService {
	
	//create
	public void createProduct(ProductDto productDto, Category category);	
	
	//update
	 public void updateProduct(ProductDto productDto, Integer productId) throws Exception;
	
	
	//delete
	public void deleteproductDto(Integer productId);
	
	
	//get single product by id
	public ProductDto getproductDto(Product product );
	
	
	//get all Product
	 public List<ProductDto> getAllProducts();

	 public Product findById(Integer productId) throws ProductNotExistsException ;
		   


	





	


	
	
	
	
	
	

}
