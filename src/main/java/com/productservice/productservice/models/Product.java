package com.productservice.productservice.models;

import com.productservice.productservice.dtos.GenericProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products") // NOTE 116 : this will help to create Document
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
  @OneToOne(
      cascade = {
        CascadeType.REMOVE,
        CascadeType
            .PERSIST // from a parent to a child entity. When we save the Product entity, the price
        // entity will also get saved.
      }) // casecade will delete the depended element in price also
  private Price price;

  private int inventoryCount;

  // NOTE 93 : create a converter to produce generic product dto from product
  public GenericProductDto from(Product product) {
    GenericProductDto genericProductDto = new GenericProductDto();
    genericProductDto.setTitle(product.getTitle());
    genericProductDto.setDescription(product.getDescription());
    genericProductDto.setImage(product.getImage());
    return genericProductDto;
  }
}
