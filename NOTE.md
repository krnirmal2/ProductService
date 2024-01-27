1. create the project with spring intialiser and choose the maven
2. As the depenedency resolver
3. now create the models
4. create base class and other modeles
5. now create controllers
6. now map the controller with end point
7. mapping with all the CRUD operation controller services
8. @Service will create object of a class using constructor and store ( we will create 
9. constructor / object for class which has implement the interface not the actual interface
10. as we know that interface have no object)
9. in ApplicationContext initialiser
10. in beans during run you will find all the object created by @service annotation
11. NOTE: WE HAVE TO GIVE A BEAN NAME EACH SERVICE CLASS WHEN IMPLEMENTED VIA INTERFACE
12. AND PASSED THE BEAN NAME WHEN CALL FROM THE CONTROLLER
13. @Qualifier("fakeStoreProductService") is defined that which service object we need in controller
14.    RestTemplate restTemplate = restTemplateBuilder.build(); // TODO: BUILDER DESGIN PATTERN NOTE   THIS METHOD USE BUILDER DESIGN PATTERN
15. create a controller service"@ControllerAdvice "which will handle all the exception from the all the class
16. and in this above class we will handle different type of handler method for each specific class
17.  @ExceptionHandler(ProductNotFoundException.class)
     @ResponseStatus(HttpStatus.NOT_FOUND) // this will change the status code of the exception
     @ResponseBody() // this will help to give what we just send value in the body not other traces

18. if you deLombok the @GETTER then it will replace the code behind it was implemented  and like wise others methods also
19. @Data ,@Builder @AllArgConstruct all are essentiall lomvok method use
20. CLASS: Finishing API's
21.     CREATE  a class to integrating third party Api like adaptor design pattern but as an class not interface
22.     Create application.property key value pair and use in place of url with @value annotation and pass the value to constructor
23.     to initialise the things 
24.     change server port 
25.     Read protobuf inplace serialisation and deserialisation
21.      
20. 
21. 
22. 
23. // Three way of Dependency Injection
1. Constructor injection // MOST USED AS WE HAVE IMPLEMENTED IN THE PRODUCT CONTROLLER
2. Field Injection  // NOT RECOMENDEND USING @AUTOWIRED
3. Setter Injection