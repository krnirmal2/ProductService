package com.productservice.productservice.dtos;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDtos implements Serializable { // NOTE 121: for redis
  // as for redis this is key value pair not for entire database as
  // we can create multiple databases in radis
  private Long id;
  private int price;

  private String title;
  private String category;
  private String description;
  private String image;
}
