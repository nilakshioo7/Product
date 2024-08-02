package com.Product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Product.entity.Category;
import com.Product.entity.Product;
import com.Product.repository.CategoryRepository;
import com.Product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Product add(Product product) {
		Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAll() {		
		return productRepository.findAll();
	}

	@Override
	public Product fet(Long id) {
		return productRepository.findById(id).orElseThrow(()->new RuntimeException("Product Not Found !"));
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);	
	}

	@Override
	public Product update(Long id, String name, double price) {
		Optional<Product> optionalCategory = productRepository.findById(id);
		if (optionalCategory.isPresent()) {
			Product product = optionalCategory.get();         
			product.setName(name);
			product.setPrice(price);
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
		
	}

}
