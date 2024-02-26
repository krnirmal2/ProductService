package com.productservice.productservice.controllers;

import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import com.productservice.productservice.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products") // this is the base endpoint for all the controller in this class
public class ProductController {
  private ProductService productService;
  // constructor injection
  public ProductController(
      @Qualifier("fakeStoreProductService") // NOTE 77: remove this when we want to use @primary
          // service
          ProductService productService) { // as we have not say product controller that
    // that productService has been annotated with @service than it will give error
    this.productService = productService;
  }

  // here the url will hit for different service CRUD
  //  todo  @GetMapping("/products/{id}") // mapped the url and in the curly braces there is
  // parameter or key that need to send
  // from the restApi
  /* NOTE 119: this code comment because post required Authorization header , so we do required = false
  @GetMapping("/{id}")
   public GenericProductDto getProductById(
       @RequestHeader(HttpHeaders.AUTHORIZATION)
           String
               token, // NOTE 19A : connecting two microservice , here we validate the authorisation
       @PathVariable("id") Long id)
       throws ProductNotFoundException { // passed the id as variable*/
  @GetMapping("/{id}")
  public GenericProductDto getProductById(
      @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false)
          String
              token, // NOTE 19A : connecting two microservice , here we validate the authorisation
      @PathVariable("id") Long id)
      throws ProductNotFoundException { // passed the id as variable

    // call the FakeStoreProductService  getProductById() method service

    return productService.getProductById(token, id);
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

  /*

    //NOTE 11 : ONE WAY OF HANDLING EXCEPTION
    // we should handle the exception when any class throw and exception
    // thats why we need to use @ExceptionHandler Annotation which tells the
    // below method is exception handler
    // this handler will handler "ProductNotFoundException"
    // when ever in this paritcular controller class face any problem
    @ExceptionHandler(ProductNotFoundException.class) //// also need to provide the type which we it should be handle in the parameter
    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
  //    System.out.printf("product id is not not availble");

      //NOTE 13:
      // change the return type to ExceptionDto
      // which will provide a specific error message to the client
      //first create excption dtos and then responseEntity for error code change
     ExceptionDto exceptionDto = new ExceptionDto();
     exceptionDto.setMessage(productNotFoundException.getMessage());
     exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);

      ResponseEntity responseEntity = new ResponseEntity(exceptionDto,HttpStatus.NOT_FOUND);
      //NOTE 14:
      //change return type from exceptionDto to responseEntity
     return responseEntity;


    }

   */
}
