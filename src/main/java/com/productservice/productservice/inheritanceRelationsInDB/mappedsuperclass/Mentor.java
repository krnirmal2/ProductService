package com.productservice.productservice.inheritanceRelationsInDB.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ms_mentor") // change manual name here
public class Mentor extends User {
  private double avgRating;
}
