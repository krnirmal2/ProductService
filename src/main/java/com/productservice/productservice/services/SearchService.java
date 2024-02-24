package com.productservice.productservice.services;

import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.models.Product;
import com.productservice.productservice.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

  // NOTE 92: need to integrate repository of product to fetch the required
  // details from db
  private ProductRepository productRepository;

  public SearchService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<GenericProductDto> searchProducts(String query, int pageNumber, int itemPerPage) {
    // Return list of products

    // NOTE 97: we will use the PageRequest class and
    PageRequest pageRequest = PageRequest.of(pageNumber, itemPerPage);
    List<Product> products = productRepository.findByTitleContaining(query, pageRequest);
    List<GenericProductDto> genericProductDtos = new ArrayList<>();

    for (Product product : products) {
      genericProductDtos.add(product.from(product));
    }
    return genericProductDtos;
  }
}
