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
import com.Product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products);
    }
	
	@GetMapping("getProdById/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable Long id) {
		Product product = productService.fet(id);
		if (null != product) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product savedProduct = productService.add(product);
		return ResponseEntity.ok(savedProduct);
	}

	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Product> updateCategory(@PathVariable Long id, @RequestBody Product product) {
		try {
			Product updatedProduct = productService.update(id, product.getName(), product.getPrice());
			return ResponseEntity.ok(updatedProduct);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("deleteProdById/{id}")
	public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
		try {
			productService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
