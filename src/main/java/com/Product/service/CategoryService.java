package com.Product.service;

import java.util.List;

import com.Product.entity.Category;

public interface CategoryService {

	Category add(Category category);
	
	Category update(Long id, String name);
	
	List<Category> getAll();
	
	Category fet(Long id);
	
	void deleteById(Long id);
}
