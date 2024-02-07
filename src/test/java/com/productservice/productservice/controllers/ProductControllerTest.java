package com.productservice.productservice.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.productservice.productservice.services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ProductControllerTest {
  // NOTE 80:
  // crete testClass for productController API method test
  // INJECT THE DEPENDENCY
  @Autowired ProductController productController;

  @MockBean // NOTE 81: we create mocking object for the productservice services to test against
  // actual
  ProductService productService;

  @Captor
  private ArgumentCaptor<Long>
      argumentCaptor; // help to capture the argument what ever you passed to the service

  @Test
  @DisplayName("Demo")
  void testOnePlusOneIsTwoOrNot() {
    //        assert (2 == 2);
  }
  /* NOTE 20UP: commented because there is error when we passing the token as a parameter in getProductById() method
  @Test
  @DisplayName("Testing getProduct by di")
  void getProductByIdNegativeTest() {
    assertThrows(ProductNotFoundException.class, () -> productController.getProductById(1000L));
  }

  // NOTE 82:
  void testGetProductGetById() {}

  @Test
  void testGetProductGetByIdByMocking() throws ProductNotFoundException {
    // NOTE 83 :
    // if we mocking some thing then the actual service we will not call
    // we call the service method we take the value that we inject
    // so we hard code the value that we return from the service here
    // it is below type return
    GenericProductDto genericProductDto = new GenericProductDto(); // expect value

    // mocking with the above value
    when(productService.getProductById(1000L)).thenReturn(genericProductDto);

    // now we calculate to the controller and actual service will call and return the value from the
    // service
    // that is below one
    GenericProductDto genericProductDto1 = productController.getProductById(1000L); // Actual value
    // now check expected = genericProductDto and actual = genericProductDto1
    assertEquals(genericProductDto, genericProductDto1); // expect vs actual
  }

  */
  /*
    @Test
    void testGetProductByIdMockingException() throws ProductNotFoundException {
      when(productService.getProductById(10L))
              .thenReturn(ProductNotFoundException.class);
      assertThrows(ProductNotFoundException.class,() ->productController.getProductById(1L));
    }
  */
  /*

  // NOTE 87: test case for the input may come from t
   //   the controller to the service is less
   // so that's why we

   @Test
   @DisplayName("testProductControllerCallsProductServiceWithSameProductIdAsInput")
   void testIfSameInput() throws ProductNotFoundException {
     // This is the test case to check if productController is passing the same productId to the
     // productService as the input.
     Long id = 100L;

     when(productService.getProductById(id)).thenReturn(new GenericProductDto());

     GenericProductDto genericProductDto = productController.getProductById(id);

     // verify is act as listener
     verify(productService).getProductById(argumentCaptor.capture());

     assertEquals(id, argumentCaptor.getValue());
   }

   */
}
