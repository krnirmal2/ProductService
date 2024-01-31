package com.productservice.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@MappedSuperclass // NOTE 36 : passed the attribute to the child class
public class BaseModel {
  @Id
  // NOTE 36:
  // this class use for act as parent class of primary Id
  // from where all the class get the primary key id
  // act as Dummy table
  //  NOTE 40: change Long to uuid for distributed system and
  // Generate uuid  with generator (like  TWITTER SNOW FLEX ALGORITHM)
  //  private Long id;
  @GeneratedValue(generator = "uuidGenerator") // OWN GENERATOR
  @GenericGenerator(
      name = "uuidGenerator",
      strategy = "uuid2") // STRATEGY USE "uuid2" for generate this UUID
  @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
  // use coloumn name and binary 16B format with not nullable and not updatable UUID as it is
  // primary key
  private UUID id;
}
