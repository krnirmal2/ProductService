package com.productservice.productservice.inheritanceRelationsInDB.singletable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("st_mentorRepository")
public interface MentorRepository extends JpaRepository<Mentor,Long> {
    @Override
    Mentor save (Mentor mentor);
    // but Mentor that is return that have id attribute

}
