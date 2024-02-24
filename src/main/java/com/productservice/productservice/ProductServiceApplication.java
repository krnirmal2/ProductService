package com.productservice.productservice;

import com.productservice.productservice.inheritanceRelationsInDB.singletable.*;
import com.productservice.productservice.repository.CategoryRepository;
import com.productservice.productservice.repository.PriceRepository;
import com.productservice.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {
  /*
         implements CommandLineRunner

   NOTE 85: commented for not intialised any unnecssary object
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
  */

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

  // NOTE 95:
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
  private final PriceRepository priceRepository;

  @Autowired
  public ProductServiceApplication(
      @Qualifier("productRepo") ProductRepository productRepository,
      CategoryRepository categoryRepository,
      PriceRepository priceRepository) {
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
    this.priceRepository = priceRepository;
  }

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
  //  @Override
  //  @Transactional// NOTE 66 : when we implement lock and the query will run as a single query And
  // commented if it is Eager Fetching

  /* public void run(String... args) throws Exception {
  // NOTE 54:
  // helps
  // Category
  */
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
  /*
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
  /*
   */
  /*
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
     //
     //    priceRepository.deleteById(UUID.fromString("acefc21b-175c-4e04-b396-ad0e19c4550d"));
  */
  /*

  */
  /* // Note 65
  // Lazy and Eager fetch
  Category category = new Category();
  category.setName("Apple Device");
  Category savedCategory = categoryRepository.save(category);

  Price price = new Price();
  price.setValue(100000);
  price.setCurrency("INR");

  Product product1 = new Product();
  product1.setPrice(price);
  product1.setTitle("iPhone 15 pro max");
  product1.setDescription("Best iPhone ever.");
  product1.setImage("IMG");
  product1.setCategory(category);
  Product savedProduct = productRepository.save(product1);

  Price price1 = new Price();
  price1.setValue(100000);
  price1.setCurrency("INR");

  Product product2 = new Product();
  product2.setPrice(price1);
  product2.setTitle("iPhone 15 pro max");
  product2.setDescription("Best iPhone ever.");
  product2.setImage("IMG");
  product2.setCategory(category);
  Product savedProduct1 = productRepository.save(product2);

  // Need to set different price otherwise we will get error

  Price price2 = new Price();
  price2.setValue(100000);
  price2.setCurrency("INR");

  Product product3 = new Product();
  product3.setPrice(price2);
  product3.setTitle("iPhone 15 pro max");
  product3.setDescription("Best iPhone ever.");
  product3.setImage("IMG");
  product3.setCategory(category);
  Product savedProduct2 = productRepository.save(product3);
  */
  /*

  //    NOTE 66:
  // IMPLEMENTATION OF LAZY FETCHING
  // lazy fetching will fetch the product when it require with out join that we
  // can see from the   private List<Product> products; it is lazy
  // Three point in Lazy loading
  //      1. it will fetch the data when required
  //              2.    when explicitly with @Transactional then it will have some Query will be
  // there
  //
  //    Optional<Category> optionalCategory =
  //        categoryRepository.findById(
  //            UUID.fromString("ecfa76b7-adbc-416f-aa5c-4222ae832419")); // copy the UUID of
  // category
  //    Category category = optionalCategory.get();
  */
  /*    NOTE 66: if we comment the below code
                  then select c1_0.id,c1_0.name from category c1_0 where c1_0.id=?
                  will run and only category item will fetch not its corresponding product

      // And when uncomment the below code we will get the join query as well and all the list of
      // product
      // along with categories
      // select
      // p1_0.category_id,p1_0.id,p1_0.description,p1_0.image,p1_0.inventory_count,p2_0.id,p2_0.currency,p2_0.value,p1_0.prices,p1_0.title from product p1_0 left join price p2_0 on p2_0.id=p1_0.price_id where p1_0.category_id=?

      List<Product> products = category.getProducts();

      for (Product product : products) {
        System.out.println(product.getTitle());
      }
  */
  /*
   */
  /* NOTE 67:
                  implement Eager Fetching by doing
                 remove @Transactional and make product as Eager by "fetch = jakarta.persistence.FetchType.EAGER"
                 and Query will run
                 select c1_0.id,c1_0.name,p1_0.category_id,p1_0.id,p1_0.description,p1_0.image,p1_0.inventory_count,p2_0.id,p2_0.currency,p2_0.value,p1_0.prices,p1_0.title from category c1_0 left join product p1_0 on c1_0.id=p1_0.category_id left join price p2_0 on p2_0.id=p1_0.price_id where c1_0.id=?

  */
  /*

  */
  /* NOTE 69:
     fetch all the product

  List<Product> products = productRepository.findAll();
   */
  /*
   */
  /* NOTE 70:
    fetch by title

  List<Product> products = productRepository.findByTitle("iPhone 15 pro max");
  */
  /*
   */
  /* NOTE 73:
         fetch by title and description

       List<Product> products = productRepository.findByTitleAndDescription("iPhone 15 pro max","Best iPhone ever.");
     for (Product product : products) {
       System.out.println(products.toString());
     }
  */
  /*

  */
  /*
     todo 74:
      // just read the all this not impmented this
          category = new Category();
          category.setName("Samsung");
          Category category1 = categoryRepository.save(category);

          Price price = new Price();
          price.setValue(49000);
          price.setCurrency("INR");

  //        Product product = new Product("Samsung Fold 5", "Samsung's Foldable phone", "XYZ", category, price);

  //        productRepository.save(product);

      List<Product> products1 = productRepository.findAllByPrice_ValueBetween(29000, 50000);
    */
  /*
  }*/
  @Override
  public void run(String... args) throws Exception {
    /*
     NOTE 97: we can comment or comment out based on our need if we generate
         product or category
        Category category = new Category();
        category.setName("Apple Device");
        Category savedCategory = categoryRepository.save(category);

        Price price = new Price();
        price.setValue(100000);
        price.setCurrency("INR");

        Product product1 = new Product();
        product1.setPrice(price);
        product1.setTitle("iPhone 15 pro max");
        product1.setDescription("Best iPhone ever.");
        product1.setImage("IMG");
        product1.setCategory(category);
        Product savedProduct = productRepository.save(product1);

        Price price1 = new Price();
        price1.setValue(100000);
        price1.setCurrency("INR");

        Product product2 = new Product();
        product2.setPrice(price1);
        product2.setTitle("iPhone 15 pro max");
        product2.setDescription("Best iPhone ever.");
        product2.setImage("IMG");
        product2.setCategory(category);
        Product savedProduct1 = productRepository.save(product2);

        // Need to set different price otherwise we will get error

        Price price2 = new Price();
        price2.setValue(100000);
        price2.setCurrency("INR");

        Product product3 = new Product();
        product3.setPrice(price2);
        product3.setTitle("iPhone 15 pro max");
        product3.setDescription("Best iPhone ever.");
        product3.setImage("IMG");
        product3.setCategory(category);
        Product savedProduct2 = productRepository.save(product3);

    */

  }
}
