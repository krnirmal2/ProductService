package com.productservice.productservice.inheritanceRelationsInDB.singletable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_ta")//Note: 50 remove each entity anotation for single table
// because all attribute present in User table
@PrimaryKeyJoinColumn(name = "user id")//NOTE 49 : this is used fir join between user table

public class Ta extends User {
    private String ta_sessions;
}
