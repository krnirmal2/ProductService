package com.productservice.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
  @Id
  private String title;
  private String description;
  private int price;
  private String image;
  /*
   NOTE 39 : CARDINALITY BTWN PRODUCT AND CATEGORY
        1    -->   1
      PRODUCT  :  CATEGORY
        M    <--   1
  */

  @ManyToOne
  private Category category; //DERIVED ATTRIBUTE AND NEED TO CARDINALITY WITH PRODUCT
}
