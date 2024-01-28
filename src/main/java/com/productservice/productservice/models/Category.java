package com.productservice.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // NOTE 37: ITS DEFINE THAT THIS WILL BE A TABLE IN DB
public class Category extends BaseModel {
  @Id // NOTE 38: Id WILL BE THE PRIMARY KEY OF BASE CLASS TO THIS
  private String name;
}
