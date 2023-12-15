package com.productservice.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
  // add all the item of model and this will act as extra layer for lossly coupled with
  // FakeStoreProductDtos
  private Long id;
  private int price;
  private String title;
  private String category;
  private String description;
  private String image;
}
