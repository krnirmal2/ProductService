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


// Three way of Dependency Injection
1. Constructor injection // MOST USED AS WE HAVE IMPLEMENTED IN THE PRODUCT CONTROLLER
2. Field Injection  // NOT RECOMENDEND USING @AUTOWIRED
3. Setter Injection