package com.productservice.productservice.repository;

import com.productservice.productservice.models.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenSearchProductRepository extends ElasticsearchRepository<Product, String> {
  @Override
  Iterable<Product> findAll();

  Optional<Product> findById(Long productId);

  Product save(Product product);

  List<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);

  List<Product> findByTitleContaining(String query, PageRequest pageRequest);
}
