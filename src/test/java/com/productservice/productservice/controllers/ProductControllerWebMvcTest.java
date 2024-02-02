package com.productservice.productservice.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.services.ProductService;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// It won't initialize the unnecessary dependencies.
@WebMvcTest(ProductController.class)
public class ProductControllerWebMvcTest {
  // NOTE 84:
  // Web MVC help to integration testing

  @MockBean private ProductService productService;

  @Inject private MockMvc mockMvc;

  @Inject private ObjectMapper objectMapper;

  @Test
  void testGetAllProductsReturnsEmptyList() throws Exception {
    when(productService.getAllProduct()).thenReturn(new ArrayList<>());

    mockMvc.perform(get("/products")).andExpect(status().is(200)).andExpect(content().string("[]"));
  }

  @Test
  void testGetAllProductsReturnsValidList() throws Exception {
    List<GenericProductDto> genericProductDtos = new ArrayList<>();
    genericProductDtos.add(new GenericProductDto());
    genericProductDtos.add(new GenericProductDto());
    genericProductDtos.add(new GenericProductDto());

    when(productService.getAllProduct()).thenReturn(genericProductDtos);

    mockMvc
        .perform(get("/products"))
        .andExpect(status().is(200))
        .andExpect(content().string(objectMapper.writeValueAsString(genericProductDtos)));
  }

  @Test
  void createProductShouldCreateAValidNewProduct() throws Exception {
    GenericProductDto productToCreateDto = new GenericProductDto();
    productToCreateDto.setTitle("Macbook");
    productToCreateDto.setPrice(200000);
    productToCreateDto.setDescription("Fastest mac ever");
    productToCreateDto.setCategory("Laptop");

    GenericProductDto outputGenericProductDto = new GenericProductDto();
    outputGenericProductDto.setCategory(productToCreateDto.getCategory());
    outputGenericProductDto.setTitle(productToCreateDto.getTitle());
    outputGenericProductDto.setPrice(productToCreateDto.getPrice());
    outputGenericProductDto.setDescription(productToCreateDto.getDescription());
    outputGenericProductDto.setId(1000L);

    when(productService.createProduct(any())).thenReturn(outputGenericProductDto);

    mockMvc
        .perform(
            post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productToCreateDto)))
        .andExpect(status().is(200))
        .andExpect(content().string(objectMapper.writeValueAsString(outputGenericProductDto)))
        .andExpect(jsonPath("$.title", is("Macbook")))
        .andExpect(jsonPath("$.price", is(200000)));
  }
}

/*

{ $
    student: {
        id:"1",
        "name":"Deepak",
        psp:
    }
}

 */
