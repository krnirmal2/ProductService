package com.productservice.productservice.inheritanceRelationsInDB.singletable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("st_userRepository")
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    User save(User user);

}
