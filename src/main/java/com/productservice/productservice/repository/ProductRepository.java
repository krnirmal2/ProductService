package com.productservice.productservice.repository;

import com.productservice.productservice.models.Category;
import com.productservice.productservice.models.Product;
import com.productservice.productservice.services.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Override
    Product save(Product product);
}
