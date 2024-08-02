package com.Product.service;

import java.util.List;

import com.Product.entity.Category;
import com.Product.entity.Product;

public interface ProductService {

	Product add(Product product);
	
	Product update(Long id, String name, double price);
	
	List<Product> getAll();
	
	Product fet(Long id);
	
	void deleteById(Long id);
}
