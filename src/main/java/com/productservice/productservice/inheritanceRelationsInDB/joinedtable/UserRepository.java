package com.productservice.productservice.inheritanceRelationsInDB.joinedtable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("join_repository")
public interface UserRepository extends JpaRepository<User, Long> {
  @Override
  User save(User user);
}
