package com.productservice.productservice.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass // NOTE 36 : passed the attribute to the child class
public class BaseModel {
  //NOTE 36:
  // this class use for act as parent class of primary Id
  // from where all the class get the primary key id
  // act as Dummy table
  private Long id;
}
