package com.productservice.productservice.controllers;

import com.productservice.productservice.dtos.FakeStoreProductDtos;
import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products") // this is the base endpoint for all the controller in this class
public class ProductController {
  private ProductService productService;
  // constructor injection
  public ProductController(
      @Qualifier("fakeStoreProductService")
          ProductService productService) { // as we have not say product controller that
    // that productService has been annotated with @service than it will give error
    this.productService = productService;
  }

  // here the url will hit for different service CRUD
  //  todo  @GetMapping("/products/{id}") // mapped the url and in the curly braces there is
  // parameter or key that need to send
  // from the restApi
  @GetMapping("/{id}")
  public FakeStoreProductDtos getProductById(
      @PathVariable("id") Long id) { // passed the id as variable

    // call the FakeStoreProductService  getProductById() method service

    return productService.getProductById(id);
  }

  @GetMapping // get method in post man
  public List<GenericProductDto> getAllProduct() {
    return productService.getAllProduct();
  }

  @DeleteMapping("/{id}")
  public GenericProductDto deleteProductById(@PathVariable("id") Long id) {
    return productService.deleteProductById(id);
  }

  @PostMapping // for post method
  public GenericProductDto createProduct(
      @RequestBody
          GenericProductDto
              genericProductDto) { // RequestBody is an annotation used in the context of a
    // controller method to indicate that a method parameter should
    // be bound to the body of the HTTP request.
    return productService.createProduct(genericProductDto);
  }

  public void updateProduct() {}
}
