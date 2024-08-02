package com.Product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Product.entity.Category;
import com.Product.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category add(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAll() {		
		return categoryRepository.findAll();
	}

	@Override
	public Category fet(Long id) {
		return categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category Not Found !"));
	}

	@Override
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);	
	}

	@Override
	public Category update(Long id, String name) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();         
            category.setName(name);
            return categoryRepository.save(category);
        } else {
            throw new RuntimeException("Category not found with id: " + id);
        }
		
	}

}
