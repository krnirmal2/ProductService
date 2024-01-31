package com.productservice.productservice;

import com.productservice.productservice.inheritanceRelationsInDB.singletable.*;
import com.productservice.productservice.models.Category;
import com.productservice.productservice.models.Price;
import com.productservice.productservice.models.Product;
import com.productservice.productservice.repository.CategoryRepository;
import com.productservice.productservice.repository.PriceRepository;
import com.productservice.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
  private final PriceRepository priceRepository;

  public ProductServiceApplication(
      CategoryRepository categoryRepository,
      ProductRepository productRepository,
      PriceRepository priceRepository) {
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
    this.priceRepository = priceRepository;
  }

  /* private MentorRepository mentorRepository;
    ////NOTE 47:create the data memeber
    private UserRepository userRepository;
  //  private StudentRepository studentRepository;
  */
  /* NOTE
      Table per class example
  @Autowired
   ProductServiceApplication(@Qualifier("tpc_mentorRepository") MentorRepository mentorRepository,
   UserRepository userRepository){
     this.mentorRepository = mentorRepository;
     this.userRepository = userRepository;
   }*/
  /*

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
  */
  /*

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
  // NOTE 43:
  // for running SrpingBoot jpa
  @Override
  public void run(String... args) throws Exception {
    // NOTE 54:
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
    /*   NOTE 61:
          use of price model to show the example of one to one with product
          thats why this code is commented
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
            List<Product> products = category.getProducts();//get
            for (Product prduct : products) {
                System.out.println(prduct.getTitle());
            }

    */

    //      NOTE 62:
    //      Example of price
    Price price = new Price();
    price.setCurrency("INR");
    price.setValue(100000);
    priceRepository.save(price);

    Category category = new Category();
    category.setName("Apple Devices");
    Category savedCategoy = categoryRepository.save(category);

    Product product = new Product();
    product.setTitle("iPhone 15 pro");
    product.setDescription("Best iPhone ever");
    product.setCategory(savedCategoy);
    Product savedProduct = productRepository.save(product);

    // NOTE 64 :
    //   Deleting product
    //    priceRepository.deleteById(UUID.fromString("acefc21b-175c-4e04-b396-ad0e19c4550d"));
  }
}
