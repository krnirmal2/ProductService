package com.productservice.productservice.inheritanceRelationsInDB.tableperclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
//NOTE 44:
//@MappedSuperclass// rermoved the
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)//strategy of inheritance is table per class
@Entity(name = "tpc_user")
public class User {
    //NOTE 41:
    // practical related difrerent type of
    // inheritance here @MappedSuperclass has been used
    // where this parent class will have not any table
    // but child of it have table along with below attributes

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// generated incremental id

    private Long id;
    private String email;
    private String name;
}
