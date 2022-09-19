package com.fashion.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
public class Product {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    private @NotNull String name;
	    private @NotNull String imageURL;
	    private @NotNull double price;
	    private @NotNull String description;


	    // Many to one relationship
	    @ManyToOne
	    @JsonIgnore
	    @JoinColumn(name = "category_id")
	    Category category;


	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getImageURL() {
	        return imageURL;
	    }

	    public void setImageURL(String imageURL) {
	        this.imageURL = imageURL;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public Category getCategory() {
	        return category;
	    }

	    public void setCategory(Category category) {
	        this.category = category;
	    }

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }
}
