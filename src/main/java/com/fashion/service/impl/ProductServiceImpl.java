package com.fashion.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashion.dto.ProductDto;
import com.fashion.entities.Category;
import com.fashion.entities.Product;
import com.fashion.exception.ProductNotExistsException;
import com.fashion.exception.ResourceNotFoundException;
import com.fashion.repository.ProductRepo;
import com.fashion.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
		

	@Override
	public void createProduct(ProductDto productDto, Category category) {
		Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        productRepo.save(product);
        
	}

	@Override
    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        // throw an exception if product does not exists
        if (!optionalProduct.isPresent()) {
            throw new Exception("product not present");
        }
        Product product = optionalProduct.get();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productRepo.save(product);
    }
	@Override
	public void deleteproductDto(Integer productId) {
		Product prod = this.productRepo.findById(productId).orElseThrow(()->
		new ResourceNotFoundException("Product", "producId",productId));
		this.productRepo.delete(prod);
	}

	@Override
	public ProductDto getproductDto(Product product) {

        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return productDto;	}

  		
	@Override
    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: allProducts) {
            productDtos.add(getproductDto(product));
        }
        return productDtos;
    }
	
	
    public Product findById(Integer productId) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("product id is invalid: " + productId);
        }
        return optionalProduct.get();
    }
	
}
