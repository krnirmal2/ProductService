package com.productservice.productservice;

import com.productservice.productservice.inheritanceRelationsInDB.singletable.*;
import com.productservice.productservice.models.Category;
import com.productservice.productservice.models.Product;
import com.productservice.productservice.repository.CategoryRepository;
import com.productservice.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductServiceApplication(CategoryRepository categoryRepository,
                                     ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    /* private MentorRepository mentorRepository;
     ////NOTE 47:create the data memeber
     private UserRepository userRepository;
   //  private StudentRepository studentRepository;
   *//* NOTE
     Table per class example
 @Autowired
  ProductServiceApplication(@Qualifier("tpc_mentorRepository") MentorRepository mentorRepository,
  UserRepository userRepository){
    this.mentorRepository = mentorRepository;
    this.userRepository = userRepository;
  }*//*

  //NOTE 54:
  // Single table For whole class
  @Autowired
  ProductServiceApplication(@Qualifier("st_mentorRepository") MentorRepository mentorRepository,
                            @Qualifier("st_userRepository") UserRepository userRepository

  ){
    this.mentorRepository = mentorRepository;
    this.userRepository = userRepository;
  }
  */
  public static void main(String[] args) {
    SpringApplication.run(ProductServiceApplication.class, args);
  }
/*
/*  //used to save Mentor value in mentor table of table per table Inheritance
    Mentor mentor = new Mentor();
    mentor.setAvgRating(8.2);
//    mentor.setId(); this will come from parent Id
    mentor.setName("Nirmal");
    mentor.setEmail("nirmal.kumar@gmail.com");
    mentorRepository.save(mentor);


    //Note 48:
    // save user data in the table
    User user = new User();
    user.setEmail("nirmal.TablePerTable@email.com");
    user.getEmail();
    user.setName("NirmalUserInTablePerTable");

            //Get all the Users.
        List<User> users = userRepository.findAll();
        for (User user1 : users) {
            System.out.println(user1.toString());
        }
*//*

    // NOTE: 52
    //  create single table  inheritence example
        User user = new User();
        user.setName("Arshi");
        user.setEmail("arshi@gmail.com");
        userRepository.save(user);

        Mentor mentor = new Mentor();
        mentor.setName("Deepak");
        mentor.setEmail("deepak.kasera@scaler.com");
        mentor.setAvgRating(4.7);
        mentorRepository.save(mentor);

        Student student = new Student();
        student.setName("harsh");
        student.setEmail("harsh@gmail.com");
        student.setPsp(99.0);
        studentRepository.save(student);

*/
  //NOTE 43:
  //for running SrpingBoot jpa
  @Override
  public void run(String... args) throws Exception {
      //NOTE 54:
      // helps
      // Category
     /*
     NOTE 56 :
     comment out code to create multiple product from the UUID copied from category table

     Category category = new Category();
      category.setName("Apple Devices");
      Category savedCategory = categoryRepository.save(category); // saved to db

      // NOTE 55:
        // if we run this again and again then we will get below error because of UUID unique
      // could not execute statement [Duplicate entry 'Apple Devices' for key 'category.UK_46ccwnsi9409t36lurvtyljak']
      // product category

      */
     Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString("d1915ca4-a663-4fb3-ab03-c3243324dfd7"));// this will create multiple product
//       as we run as many times
              if (optionalCategory.isEmpty()) {
            throw new Exception("Category was null");
        }
        Category category = optionalCategory.get();

      Product product = new Product();
      product.setTitle("Iphone 15 pro");
      product.setDescription("Best iphone ever");
//      product.setCategory(savedCategory); //used the saved category from the db
      product.setCategory(category);// get from already saved using UUDId
      productRepository.save(product); // saved the product after getting for each run

      //Find all the products with category = Apple Devices.
        List<Product> products = category.getProducts();
        for (Product prduct : products) {
            System.out.println(prduct.getTitle());
        }


  }

}
