package com.productservice.productservice.inheritanceRelationsInDB.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //NOTE 46: we will use UserRepository to connect it with
    // db so that CRUD operation can happend
    // here we will save the value in table of user value that we will
    // set in each variable in ProductionServiceApplication

    @Override
    User  save( User user);
}
