package com.productservice.productservice.inheritanceRelationsInDB.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//NOTE 45:
@Repository("tpc_mentorRepository")// gives name of the Repository
public interface MentorRepository extends JpaRepository<Mentor,Long> {
    //NOTE 42:
    //create mentorRepository
    // for JPA use

    @Override
    Mentor save(Mentor mentor);//mentor in parameter have not id attribut
    // but Mentor that is return that have id attribute


}
