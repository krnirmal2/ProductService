package com.productservice.productservice.inheritanceRelationsInDB.mappedsuperclass;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class User {
  // NOTE 41:
  // practical related difrerent type of
  // inheritance here @MappedSuperclass has been used
  // where this parent class will have not any table
  // but child of it have table along with below attributes

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) // generated incremental id
  private Long id;

  private String email;
  private String name;
}
