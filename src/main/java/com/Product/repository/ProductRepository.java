package com.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
