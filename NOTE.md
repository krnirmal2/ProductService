# Project Development Steps (with Note Numbers)

1. **Create the project** with Spring Initializer and choose Maven as the dependency resolver.
2. **Create the models** (entities).
3. **Create a base class** and other models.
4. **Create controllers** for handling API requests.
5. **Map controllers to endpoints.**
6. **Map all CRUD operations** in controller services.
7. **@Service** will create an object of a class using the constructor and store it (we create a constructor/object for the class that implements the interface, not the interface itself, as interfaces cannot be instantiated).
8. In **ApplicationContext initializer**, during runtime, all objects created by `@Service` annotation are managed as beans.
9. **Bean naming:** Each service class implemented via an interface must be given a bean name, and this name is used when calling from the controller.
10. **@Qualifier("fakeStoreProductService")** is used to specify which service object is needed in the controller.
11. **RestTemplate restTemplate = restTemplateBuilder.build();**  
    - *Builder Design Pattern* is used here.
12. **@ControllerAdvice** is used to create a controller service that handles exceptions from all classes.
13. In the above class, handle different types of exceptions for each specific class using:
    - `@ExceptionHandler(ProductNotFoundException.class)`
    - `@ResponseStatus(HttpStatus.NOT_FOUND)` (changes the status code)
    - `@ResponseBody()` (returns only the value sent, not the stack trace)
14. **Lombok notes:**  
    - If you deLombok the `@Getter`, it will show the code it generates.
    - `@Data`, `@Builder`, `@AllArgsConstructor` are essential Lombok annotations.
15. **Finishing APIs:**  
    - Create a class to integrate third-party APIs (adapter design pattern, but as a class, not interface).
    - Use `application.properties` key-value pairs and `@Value` annotation for URLs.
    - Change server port as needed.
    - Read about protobuf for serialization/deserialization.
16. **Advanced Database Operations and DB Migrations:**  
    - Use Spring JPA, SQL connector, dependencies in `pom.xml`.
    - Handle cardinality between tables.
    - Resolve reserved keyword errors.
    - **Cardinality between Product and Category:**  
      - 1 --> 1 (Product : Category)  
      - M <-- 1 (Many Products to One Category)
17. **Database Queries, Inheritance & Relations:**  
    - Demonstrate inheritance: single table, joined table, table per class, mapped superclass.
    - Use `@Qualifier` to specify a particular class.
    - Use `@Repository` for JPA repositories.
    - Use `@PrimaryKeyJoinColumn(name = "user id")` for joins.
    - Use `@GeneratedValue(strategy = GenerationType.AUTO)` for auto-increment IDs.
    - Use `@MappedSuperclass` to pass attributes to child classes.
    - Use `@Inheritance(strategy = InheritanceType.JOINED)` for table-per-class strategy.
    - Use `@GeneratedValue(generator = "uuidGenerator")` for custom ID generation.
    - Use `@Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)` for UUID primary keys.
    - `@ManyToOne private Category category;` for product-category relationship.
18. **Database - Cardinalities & N+1 problem:**  
    - (Details not expanded in your note, but this is a placeholder for handling cardinality and N+1 query issues.)
19. **Spring Cloud - Discovery Service & API Gateway:**  
    - To connect one microservice with another:
      1. Run Service Discovery Microservice and all instances of other microservices.
      2. Create a function in ProductService to call UserService using RestTemplate and the appropriate URL.
      3. Call the endpoint in ProductService and get the response.
      4. Return the result.
20. **Three ways of Dependency Injection:**  
    - Constructor injection (most used, as in ProductController)
    - Field injection (not recommended, uses `@Autowired`)
    - Setter injection

---

# Technical Stack & Tools Used (Detailed)

## 1. Spring Boot
- **Purpose:** Main framework for building the application. Provides auto-configuration, embedded server, and production-ready features.
- **Usage:** All application logic, REST APIs, and configuration are built on top of Spring Boot.

## 2. Spring Web (spring-boot-starter-web)
- **Purpose:** Enables building RESTful web services and web applications.
- **Usage:** Used for creating controllers and exposing REST endpoints.

## 3. Spring Data JPA (spring-boot-starter-data-jpa, spring-data-jpa)
- **Purpose:** Simplifies database access and ORM (Object Relational Mapping) using JPA and Hibernate.
- **Usage:** Used for repository interfaces and entity management.

## 4. MySQL Connector (mysql-connector-j)
- **Purpose:** JDBC driver for connecting to MySQL databases.
- **Usage:** Enables the application to interact with a MySQL database.

## 5. Spring Security (spring-boot-starter-security)
- **Purpose:** Provides authentication and authorization features.
- **Usage:** Secures endpoints, integrates JWT for stateless authentication.

## 6. Spring Security OAuth2 Resource Server (spring-security-oauth2-resource-server)
- **Purpose:** Allows the service to act as a resource server, validating JWT tokens issued by an authorization server.
- **Usage:** Used for microservice security and token validation.

## 7. Lombok
- **Purpose:** Reduces boilerplate code by generating getters, setters, constructors, etc., at compile time.
- **Usage:** Used in models, DTOs, and other classes for cleaner code.

## 8. Spring Boot DevTools
- **Purpose:** Provides hot reloading and developer productivity features.
- **Usage:** Speeds up development by automatically restarting the app on code changes.

## 9. Spring Boot Configuration Processor
- **Purpose:** Generates metadata for custom configuration properties.
- **Usage:** Helps with IDE auto-completion for custom properties.

## 10. JUnit, AssertJ, Hamcrest
- **Purpose:** Testing frameworks and assertion libraries.
- **Usage:** Used for unit and integration testing of controllers and services.

## 11. Flyway (flyway-mysql)
- **Purpose:** Database migration tool.
- **Usage:** Manages and applies database schema migrations.

## 12. OpenSearch (spring-data-opensearch-starter)
- **Purpose:** Integration with OpenSearch for advanced search capabilities.
- **Usage:** Used for indexing and searching product data.

## 13. Spring Data Redis (spring-boot-starter-data-redis)
- **Purpose:** Integration with Redis for caching and fast data access.
- **Usage:** Can be used to cache product data or other frequently accessed information.

## 14. Spring Cloud Netflix Eureka Client (spring-cloud-starter-netflix-eureka-client)
- **Purpose:** Service discovery in a microservices architecture.
- **Usage:** Registers the ProductService with Eureka for dynamic discovery by other services.

## 15. RestTemplate & RestTemplateBuilder
- **Purpose:** Simplifies HTTP communication with other services/APIs.
- **Usage:** Used for calling third-party APIs (e.g., FakeStore, Flipkart) and other microservices.

## 16. @LoadBalanced RestTemplate
- **Purpose:** Enables client-side load balancing for REST calls.
- **Usage:** Distributes requests across multiple instances of a service.

## 17. Spotless Maven Plugin
- **Purpose:** Code formatting and linting.
- **Usage:** Ensures code style consistency across the project.

## 18. Spring Cloud Configurations
- **Purpose:** Centralized configuration management for microservices.
- **Usage:** Manages environment-specific properties and service discovery settings.

## 19. Qodana (qodana.yaml)
- **Purpose:** Static code analysis and quality checks.
- **Usage:** Ensures code quality and best practices in CI/CD pipelines.

---

## Key Annotations and Patterns

- **@SpringBootApplication:** Marks the main class for Spring Boot auto-configuration.
- **@EnableDiscoveryClient:** Enables service registration with Eureka.
- **@Entity, @Table, @Id, @GeneratedValue:** JPA annotations for ORM mapping.
- **@Repository:** Marks a class as a JPA repository.
- **@Service:** Marks a class as a service component.
- **@RestController, @ControllerAdvice:** For REST endpoints and global exception handling.
- **@Qualifier:** Specifies which bean to inject when multiple candidates exist.
- **@LoadBalanced:** Enables load balancing for RestTemplate.
- **@Document:** Marks a class as an OpenSearch/Elasticsearch document.
- **@ManyToOne, @OneToOne, @Inheritance, @MappedSuperclass:** JPA relationship and inheritance strategies.

---

## Configuration Files

- **application.properties:** Main configuration for database, JPA, security, Redis, OpenSearch, Eureka, etc.
- **SQL.sql:** Custom SQL scripts for database setup or queries.

---

## Design Patterns Used

- **Builder Pattern:** Used in RestTemplateBuilder for constructing RestTemplate instances.
- **Adapter Pattern:** Used for integrating third-party APIs (FakeStore, Flipkart).
- **Dependency Injection:** Used throughout the project for loose coupling and testability.

