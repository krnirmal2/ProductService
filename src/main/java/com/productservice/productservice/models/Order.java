package com.productservice.productservice.models;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

// NOTE 59
// create order table and join with product with many to many relation
@Getter
@Setter
@Entity(name = "orders")
public class Order extends BaseModel {

  /*NOTE 60:
      1              M
     Order ------- Product => M:M
       M              1
  */
  @ManyToMany
  @JoinTable( // join two table
      name = "products_orders", // table name
      joinColumns = @JoinColumn(name = "order_id"), // join by order_id
      inverseJoinColumns = @JoinColumn(name = "product_id") // with product id
      )
  private List<Product> products;
}
