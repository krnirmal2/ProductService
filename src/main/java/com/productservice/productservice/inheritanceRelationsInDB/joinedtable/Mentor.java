package com.productservice.productservice.inheritanceRelationsInDB.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "join_mentor") // change manual name here
@PrimaryKeyJoinColumn(name = "user id") // NOTE 49 : this is used for join between user table
public class Mentor extends User {
  private double avgRating;
}
