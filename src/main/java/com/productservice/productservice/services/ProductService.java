package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDtos;
import com.productservice.productservice.dtos.GenericProductDto;

import java.util.List;


public interface ProductService {
    public FakeStoreProductDtos getProductById(Long Id);
    public List<GenericProductDto> getAllProduct();
    public void deleteProductById();
    public void createProduct();
    public void updateProduct();
}
