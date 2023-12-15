package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDtos;


public interface ProductService {
    public FakeStoreProductDtos getProductById(Long Id);
    public void getAllProduct();
    public void deleteProductById();
    public void createProduct();
    public void updateProduct();
}
