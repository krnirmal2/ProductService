package com.productservice.productservice.services;

import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import java.util.List;

public interface ProductService {
  public GenericProductDto getProductById(Long Id) throws ProductNotFoundException;

  public List<GenericProductDto> getAllProduct();

  public GenericProductDto deleteProductById(Long Id);

  public GenericProductDto createProduct(GenericProductDto genericProductDto);

  public void updateProduct();
}
