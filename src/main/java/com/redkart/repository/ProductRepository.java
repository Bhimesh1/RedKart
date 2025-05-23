package com.redkart.repository;

import com.redkart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // No need to add anything for basic CRUD
}
