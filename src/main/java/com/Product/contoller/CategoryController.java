package com.Product.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Product.entity.Category;
import com.Product.entity.Product;
import com.Product.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/getAllCategories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> category = categoryService.getAll();
        return ResponseEntity.ok(category);
    }
	
	@GetMapping("getCatById/{id}")
	public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
		Category category = categoryService.fet(id);
		if (null != category) {
			return ResponseEntity.ok(category);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/addCategory")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		if (category.getProducts() != null) {
            for (Product product : category.getProducts()) {
                product.setCategory(category); 
            }
        }
		Category savedCatagory = categoryService.add(category);
		return ResponseEntity.ok(savedCatagory);
	}

	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
		try {
			Category updatedCatagory = categoryService.update(id, category.getName());
			return ResponseEntity.ok(updatedCatagory);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("deleteCatById/{id}")
	public ResponseEntity<Void> deleteCatagoryById(@PathVariable Long id) {
		try {
			categoryService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
