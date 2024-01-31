package com.productservice.productservice.inheritanceRelationsInDB.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "join_student")
@PrimaryKeyJoinColumn(name = "user id")//NOTE 49 : this is used fir join between user table

public class Student extends User {
    private double psp;
}
