package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDtos;
import org.springframework.stereotype.Service;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{
    @Override
    public FakeStoreProductDtos getProductById(Long Id) {
        return null;
    }

    @Override
    public void getAllProduct() {

    }

    @Override
    public void deleteProductById() {

    }

    @Override
    public void createProduct() {

    }

    @Override
    public void updateProduct() {

    }
}
