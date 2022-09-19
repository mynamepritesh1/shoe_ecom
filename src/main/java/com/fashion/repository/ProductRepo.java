package com.fashion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashion.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	Optional<Product> findById(Product product);
	
	

}
