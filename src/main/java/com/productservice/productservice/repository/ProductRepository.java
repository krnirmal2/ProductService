package com.productservice.productservice.repository;

import com.productservice.productservice.models.Product;
import java.awt.*;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("productRepo")
public interface ProductRepository extends JpaRepository<Product, UUID> {
  // NOTE 68:
  // get all the product method using query
  @Override
  // select * from product
  List<Product> findAll();

  @Override
  Product save(Product product);

  // NOTE 70:
  // create our own method for finding product by its title
  // and the corresponding query will change with this

  // NOTE 71:
  // create our own method for finding product by its title and description
  // and the corresponding query will change with this
  List<Product> findByTitleAndDescription(String title, String desc);

  //  List<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
  /*
   todo 74:
    @Override
    <S extends Product> List<S> findAll(Example<S> example);
    List<Product> findAllByPrice_ValueLessThan(Integer x);

    //@Query(value = "select * from product where id = 1", nativeQuery = true)
    List<Product> findAllByPrice_ValueBetween(Integer x, Integer y);

  */
  List<Product> findAllByPrice_ValueLessThan(Integer x);

  @Override
  <S extends Product> List<S> findAll(Example<S> example);
  // @Query(value = "select * from product where id = 1", nativeQuery = true)
  List<Product> findAllByPrice_ValueBetween(Integer x, Integer y);

  List<Product> findAllByTitle(String title);
  // NOTE 96: fetch product by it title contains the key that is passing in request like "iphone"
  // now we can use pageable interface to this
  List<Product> findByTitleContaining(String title, Pageable pageable);
}
