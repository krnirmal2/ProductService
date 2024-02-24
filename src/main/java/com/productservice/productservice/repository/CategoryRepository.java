package com.productservice.productservice.repository;

import com.productservice.productservice.models.Category;
import java.util.UUID;
// NOTE 52:
// create repository for category Model
// which helps to connect data base to CRUD operation for category model class
// variable
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
  //  @Override
  //  Category save(Category category);
}
