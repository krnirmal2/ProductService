package com.productservice.productservice.services;

import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import com.productservice.productservice.models.Product;
import com.productservice.productservice.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary // NOTE 76: this service will be the primary and no need for qualifer for distinguish in
// productcontroller
@Service
public class SelfProductServiceImpl implements ProductService {
  // need to require product Repostitory
  ProductRepository productRepository;

  SelfProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public GenericProductDto getProductById(Long Id) throws ProductNotFoundException {
    // NOTE 75 : from db we need to fetch the data not from the fake store database
    GenericProductDto genericProductDto = new GenericProductDto();
    Optional<Product> optionalProduct =
        productRepository.findById(
            UUID.fromString("9fa06bde-3787-4199-88a3-98d8d3ccbee7")); // Any random UUID value

    Product product = optionalProduct.get();

    genericProductDto.setDescription(product.getDescription());
    genericProductDto.setTitle(product.getTitle());
    genericProductDto.setImage(product.getImage());
    genericProductDto.setCategory(product.getCategory().toString());
    return genericProductDto;
  }

  @Override
  public List<GenericProductDto> getAllProduct() {
    return null;
  }

  @Override
  public GenericProductDto deleteProductById(Long Id) {
    return null;
  }

  @Override
  public GenericProductDto createProduct(GenericProductDto genericProductDto) {
    return null;
  }

  @Override
  public void updateProduct() {}
}
