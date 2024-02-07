package com.productservice.productservice.services;

import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import java.util.List;

public interface ProductService {
  public GenericProductDto getProductById(String authToken, Long Id)
      throws ProductNotFoundException; // NOTE 21 UP: add String authToken and refactor each place

  public List<GenericProductDto> getAllProduct();

  public GenericProductDto deleteProductById(Long Id);

  public GenericProductDto createProduct(GenericProductDto genericProductDto);

  public void updateProduct();
}
