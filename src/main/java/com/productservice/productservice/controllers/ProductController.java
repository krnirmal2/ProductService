package com.productservice.productservice.controllers;


import com.productservice.productservice.dtos.FakeStoreProductDtos;
import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")// this is the base endpoint for all the controller in this class
public class ProductController {
    private ProductService productService;
    //constructor injection
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {// as we have not say product controller that
        // that productService has been annotated with @service than it will give error
        this.productService = productService;
    }

    // here the url will hit for different service CRUD
    //  todo  @GetMapping("/products/{id}") // mapped the url and in the curly braces there is parameter or key that need to send
    // from the restApi
    @GetMapping("/{id}")
    public FakeStoreProductDtos getProductById(@PathVariable("id") Long id){//passed the id as variable

        //call the FakeStoreProductService  getProductById() method service



        return productService.getProductById(id);
    }
    @GetMapping
     public List<GenericProductDto> getAllProduct(){
        return productService.getAllProduct();

    }
     public void deleteProductById(){

    }
     public void createProduct(){

    }
    public void updateProduct(){

    }

}
