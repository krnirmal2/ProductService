package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDtos;
import com.productservice.productservice.dtos.GenericProductDto;
import java.util.List;

public interface ProductService {
  public FakeStoreProductDtos getProductById(Long Id);

  public List<GenericProductDto> getAllProduct();

  public GenericProductDto deleteProductById(Long Id);

  public GenericProductDto createProduct(GenericProductDto genericProductDto);

  public void updateProduct();
}
