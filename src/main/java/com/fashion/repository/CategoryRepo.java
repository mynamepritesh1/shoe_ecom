package com.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fashion.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
