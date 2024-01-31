package com.productservice.productservice.inheritanceRelationsInDB.joinedtable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("join_mentorRepository") // gives name of the Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
  // create mentorRepository
  // for JPA use

  @Override
  Mentor save(Mentor mentor); // mentor in parameter have not id attribut
  // but Mentor that is return that have id attribute

}
