package com.productservice.productservice;

import com.productservice.productservice.inheritanceRelationsInDB.singletable.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {
  private MentorRepository mentorRepository;
  ////NOTE 47:create the data memeber
  private UserRepository userRepository;
//  private StudentRepository studentRepository;
/* NOTE
     Table per class example
 @Autowired
  ProductServiceApplication(@Qualifier("tpc_mentorRepository") MentorRepository mentorRepository,
  UserRepository userRepository){
    this.mentorRepository = mentorRepository;
    this.userRepository = userRepository;
  }*/

  //NOTE 54:
  // Single table For whole class
  @Autowired
  ProductServiceApplication(@Qualifier("st_mentorRepository") MentorRepository mentorRepository,
                            @Qualifier("st_userRepository") UserRepository userRepository

  ){
    this.mentorRepository = mentorRepository;
    this.userRepository = userRepository;
  }
  public static void main(String[] args) {
    SpringApplication.run(ProductServiceApplication.class, args);
  }

  //NOTE 43:
  //for running SrpingBoot jpa
  @Override
  public void run(String... args) throws Exception {
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

//        Student student = new Student();
//        student.setName("harsh");
//        student.setEmail("harsh@gmail.com");
//        student.setPsp(99.0);
//        studentRepository.save(student);


  }
}
