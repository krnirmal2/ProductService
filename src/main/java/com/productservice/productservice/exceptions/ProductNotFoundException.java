package com.productservice.productservice.exceptions;

public class ProductNotFoundException extends Exception {

  // NOTE 10:
  // product not handled properly
  public ProductNotFoundException(Long prodcutWithId) {
    System.out.println("product with id " + prodcutWithId + "is not found");
  }
}
