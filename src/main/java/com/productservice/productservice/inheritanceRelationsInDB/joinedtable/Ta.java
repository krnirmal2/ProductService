package com.productservice.productservice.inheritanceRelationsInDB.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "join_ta")
@PrimaryKeyJoinColumn(name = "user id") // NOTE 49 : this is used fir join between user table
public class Ta extends User {
  private String ta_sessions;
}
