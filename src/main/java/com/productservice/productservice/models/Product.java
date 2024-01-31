package com.productservice.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
  //  @Id NOTE 54 : comment out for getting error
  //   getting error  Error creating bean with name 'productServiceApplication': Unsatisfied
  // dependency expressed through
  //   constructor parameter 0: Error creating bean with name 'categoryRepository' defined in com
  private String title;
  private String description;
  private int prices;
  private String image;
  /*
   NOTE 39 : CARDINALITY BTWN PRODUCT AND CATEGORY
        1    -->   1
      PRODUCT  :  CATEGORY
        M    <--   1

    Means -- Category id should be present in Product side as product is more == >
    here -- @ManyToOne should be present in product side
  */

  @ManyToOne() private Category category; // DERIVED ATTRIBUTE AND NEED TO CARDINALITY WITH PRODUCT

  /*
   NOTE 63 : CARDINALITY BTWN PRODUCT AND PRICE
        1    -->   1
      PRODUCT  :  PRICE
        1    <--   1

  */
  @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
  private Price price;

  private int inventoryCount;
}
