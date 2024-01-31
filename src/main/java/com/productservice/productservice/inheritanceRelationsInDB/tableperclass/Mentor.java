package com.productservice.productservice.inheritanceRelationsInDB.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_mentor") // change manual name here
public class Mentor extends User {
  private double avgRating;
}
