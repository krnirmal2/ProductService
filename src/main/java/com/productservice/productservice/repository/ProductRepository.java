package com.productservice.productservice.repository;

import com.productservice.productservice.models.Product;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
  @Override
  Product save(Product product);
}
