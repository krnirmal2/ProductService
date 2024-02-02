package com.productservice.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SomeModel extends BaseModel {
  @Id
  // NOTE 78:
  // we using this to checking the
  // Db version mirgation if some thing new added or deleted
  // if the ddo is not set to update in properties files
  // then we need to manually done or write sql query for new changes
  // but JPA buddy will helps to create on behalf of us
  // so it is easily we can done

  private String name;

  private int id;
}
