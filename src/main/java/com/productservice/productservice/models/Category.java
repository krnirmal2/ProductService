package com.productservice.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // NOTE 37: ITS DEFINE THAT THIS WILL BE A TABLE IN DB
public class Category extends BaseModel {

  @Column(
      nullable = false,
      unique = true) // NOTE 38: Id WILL BE THE PRIMARY KEY OF BASE CLASS TO THIS
  private String name;

  // NOTE 53:
  // we have to take lot of product of same categories
  @OneToMany(
      fetch = jakarta.persistence.FetchType.EAGER,
      mappedBy = "category") // NOTE 58: add Eager from Lazy fetch
  // this will not create a any relation extra table as
  // mappedBy helps to decide spring boot that this relation already made with category so no need
  // to create
  // extra table for redunt
  private List<Product> products;
}
