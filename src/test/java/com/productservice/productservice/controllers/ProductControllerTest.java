package com.productservice.productservice.controllers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.productservice.productservice.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ProductControllerTest {
  // NOTE 80:
  // crete testClass for productController API method test
  // INJECT THE DEPENDENCY
  @MockBean // NOTE 81:
  @Autowired
  ProductController productController;

  @Test
  @DisplayName("Demo")
  void testOnePlusOneIsTwoOrNot() {
    //        assert (2 == 2);
  }

  @Test
  @DisplayName("Testing getProduct by di")
  void getProductByIdNegativeTest() {
    //        assert (2 == 2);
    assertThrows(ProductNotFoundException.class, () -> productController.getProductById(1000L));
  }
}
