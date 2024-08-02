package com.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Product.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
