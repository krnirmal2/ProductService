package com.productservice.productservice.inheritanceRelationsInDB.singletable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
// @MappedSuperclass// rermoved the
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // strategy of inheritance is singletable
@Entity(name = "st_user")
public class User {
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
