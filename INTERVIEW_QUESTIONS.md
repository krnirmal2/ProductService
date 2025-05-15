# ProductService Project - Interview Questions & Answers

---

## 1. Project Architecture & Design

**Q:** What is the overall architecture of the ProductService project?  
**A:**  
ProductService is a Spring Boot-based microservice following a layered architecture (Controller, Service, Repository, Model/DTO). It uses Eureka for service discovery, integrates with MySQL for persistence, OpenSearch for search, Redis for caching, and external APIs (FakeStore, Flipkart) via the adapter pattern. Security is handled with JWT and Spring Security.

---

**Q:** How does ProductService ensure loose coupling between internal models and API contracts?  
**A:**  
By using Data Transfer Objects (DTOs) for all API communication. Internal models are mapped to DTOs before sending responses, and incoming requests are mapped from DTOs to models.

---

**Q:** What design patterns are used in this project?  
**A:**  
- **Adapter Pattern:** For integrating third-party APIs (FakeStore, Flipkart).
- **Builder Pattern:** For constructing RestTemplate instances.
- **Dependency Injection:** For loose coupling and testability.
- **Controller Advice Pattern:** For centralized exception handling.

---

**Q:** How is service discovery implemented?  
**A:**  
Using Netflix Eureka. ProductService registers itself as a Eureka client, enabling dynamic discovery and communication with other microservices.

---

## 2. Spring Boot & Layered Structure

**Q:** What are the main layers in this project and their responsibilities?  
**A:**  
- **Controller Layer:** Handles HTTP requests/responses.
- **Service Layer:** Contains business logic.
- **Repository Layer:** Handles data persistence.
- **Model/DTO Layer:** Defines entities and data transfer objects.

---

**Q:** How are exceptions handled globally?  
**A:**  
Using `@ControllerAdvice` and custom exception classes. All exceptions are caught and handled in a centralized way, returning meaningful error responses.

---

## 3. Persistence & Database

**Q:** How is data persisted in ProductService?  
**A:**  
Using Spring Data JPA with MySQL. Entities are mapped using JPA annotations, and repositories extend JpaRepository for CRUD operations.

---

**Q:** How is inheritance handled in the database layer?  
**A:**  
The project demonstrates various JPA inheritance strategies: single table, joined table, table per class, and mapped superclass, each in its own package.

---

**Q:** How is the N+1 select problem addressed?  
**A:**  
By using proper JPA fetch strategies (e.g., `@ManyToOne(fetch = FetchType.LAZY)`), and, if needed, using `@EntityGraph` or fetch joins in queries.

---

## 4. Security

**Q:** How is authentication and authorization implemented?  
**A:**  
Using Spring Security with JWT. Endpoints are protected, and JWT tokens are validated for each request. Role-based access control is also implemented.

---

**Q:** How does the service validate JWT tokens?  
**A:**  
With Spring Security's OAuth2 Resource Server support and a custom `TokenValidator` class.

---

## 5. External Integrations

**Q:** How does ProductService interact with third-party APIs?  
**A:**  
Via the adapter pattern. Each third-party API (e.g., FakeStore, Flipkart) has its own adaptor class implementing a common interface, allowing easy swapping or addition of new providers.

---

**Q:** How is load balancing achieved when calling other microservices?  
**A:**  
By using a `@LoadBalanced` RestTemplate, which distributes requests across available service instances registered in Eureka.

---

## 6. Search & Caching

**Q:** How is search functionality implemented?  
**A:**  
Using OpenSearch. Products are indexed and searched using the OpenSearch repository.

---

**Q:** How is caching handled?  
**A:**  
Spring Data Redis is included for caching frequently accessed data, though its use is optional/configurable.

---

## 7. Testing & Quality

**Q:** What testing frameworks are used?  
**A:**  
JUnit, AssertJ, and Hamcrest for unit and integration testing. WebMvcTest is used for controller tests.

---

**Q:** How is code quality maintained?  
**A:**  
With Spotless for code formatting and Qodana for static code analysis.

---

## 8. Configuration & Deployment

**Q:** How are environment-specific configurations managed?  
**A:**  
Via `application.properties` and environment variables. Spring profiles can be used for different environments.

---

**Q:** How is the application deployed and scaled?  
**A:**  
It is stateless and can be horizontally scaled. Service discovery and load balancing are handled by Eureka and Spring Cloud.

---

## 9. Troubleshooting & Best Practices

**Q:** What are some common errors and their solutions in this project?  
**A:**  
- **JPA query method naming issues:** Ensure repository method names follow Spring Data conventions.
- **Bean definition conflicts:** Avoid duplicate bean names or enable bean overriding.
- **Missing request headers:** Use `required = false` for optional headers in controllers.

---

**Q:** Why is it important to use DTOs instead of exposing entities directly?  
**A:**  
To decouple internal data models from API contracts, prevent overexposing data, and allow flexibility in API evolution.

---

**Q:** How do you handle microservice-to-microservice communication securely?  
**A:**  
By validating JWT tokens on each request and using HTTPS for communication.

---

## 10. Advanced

**Q:** How would you add a new third-party product provider?  
**A:**  
Implement the `ThirdPartyInterface` in a new adaptor class, configure its endpoints in `application.properties`, and register it as a Spring bean.

---

**Q:** How would you migrate the database schema in production?  
**A:**  
By using Flyway for versioned, repeatable database migrations.

---

# Scenario-Based Interview Questions & Answers

---

### 1. Scenario: Adding a New Product Provider

**Q:** Suppose your business wants to integrate a new third-party product provider (e.g., Amazon). What steps would you take to add this integration to ProductService?

**A:**  
- Implement the `ThirdPartyInterface` in a new adaptor class (e.g., `AmazonClientAdaptor`).
- Configure the new provider's API endpoints in `application.properties`.
- Register the new adaptor as a Spring bean.
- Update the service layer to use the new provider where appropriate (possibly via a strategy or factory pattern if dynamic selection is needed).
- Add tests for the new integration.

---

### 2. Scenario: Handling a Breaking Change in a Third-Party API

**Q:** If FakeStore changes its API response format, how would you ensure your service continues to work?

**A:**  
- Update the corresponding DTO (`FakeStoreProductDtos`) to match the new response.
- Adjust the adaptor class to map the new response to your internal DTOs.
- Add/modify tests to cover the new response structure.
- If possible, use feature flags or versioning to support both old and new formats during migration.

---

### 3. Scenario: Performance Bottleneck in Product Search

**Q:** Users report that product search is slow. How would you diagnose and resolve this?

**A:**  
- Profile the search endpoint to identify bottlenecks (e.g., database, OpenSearch, network).
- Check if OpenSearch indices are properly configured and optimized.
- Ensure queries are using appropriate filters and pagination.
- Consider caching frequent queries with Redis.
- Review logs and metrics for slow queries or errors.

---

### 4. Scenario: Data Consistency Between MySQL and OpenSearch

**Q:** How do you ensure that product data in MySQL and OpenSearch stays consistent?

**A:**  
- Use transactional event listeners or application events to trigger OpenSearch updates after successful DB transactions.
- Implement a reconciliation job that periodically checks and syncs data between MySQL and OpenSearch.
- Use distributed transactions or eventual consistency patterns if strict consistency is not required.

---

### 5. Scenario: Securing Sensitive Endpoints

**Q:** You need to restrict access to certain endpoints (e.g., product creation) to admin users only. How would you implement this?

**A:**  
- Use Spring Security's method-level security (`@PreAuthorize("hasRole('ADMIN')")`).
- Ensure JWT tokens include user roles/authorities.
- Configure security rules in `SpringSecurityConfig` to restrict endpoint access based on roles.

---

### 6. Scenario: Handling a Surge in Traffic

**Q:** If your service experiences a sudden spike in traffic, what measures would you take to maintain availability?

**A:**  
- Ensure the service is stateless and can be scaled horizontally (add more instances).
- Use Eureka for service discovery and load balancing.
- Implement caching for frequently accessed data.
- Use circuit breakers and rate limiting to protect downstream services.
- Monitor system metrics and set up alerts for resource exhaustion.

---

### 7. Scenario: Database Migration Failure

**Q:** What would you do if a Flyway migration fails during deployment?

**A:**  
- Check Flyway logs for the error details.
- Roll back the migration if possible, or fix the migration script and re-run.
- Ensure migrations are tested in staging before production.
- Use versioned, idempotent migration scripts to avoid partial failures.

---

### 8. Scenario: Exposing Internal Data by Mistake

**Q:** A developer accidentally exposes sensitive fields in the Product entity via the API. How do you prevent this?

**A:**  
- Always use DTOs for API responses, never expose entities directly.
- Review and restrict fields included in DTOs.
- Use code reviews and static analysis tools to catch such issues.
- Add tests to verify that sensitive data is not present in API responses.

---

### 9. Scenario: Adding a New Field to Product

**Q:** How would you add a new field (e.g., `brand`) to the Product entity and expose it via the API?

**A:**  
- Add the `brand` field to the `Product` entity and update the database schema (via Flyway migration).
- Add the field to the relevant DTOs.
- Update mapping logic between entity and DTO.
- Update controller, service, and repository logic if needed.
- Add/modify tests to cover the new field.

---

### 10. Scenario: Debugging a 500 Internal Server Error

**Q:** A client reports a 500 error when calling `/products/{id}`. How do you debug and resolve this?

**A:**  
- Check application logs for stack traces and error messages.
- Verify if the product with the given ID exists; handle `ProductNotFoundException` gracefully.
- Ensure the controller method is handling all required headers and parameters.
- Add or improve exception handling to return meaningful error responses.
- Write tests to reproduce and fix the issue.

---

### 11. Scenario: Microservice-to-Microservice Authentication

**Q:** How do you ensure that only authorized microservices can call your ProductService endpoints?

**A:**  
- Require JWT tokens for all inter-service communication.
- Validate tokens using Spring Security's OAuth2 Resource Server.
- Use service accounts or client credentials for machine-to-machine authentication.
- Optionally, use mutual TLS for added security.

---

### 12. Scenario: Rolling Back a Failed Deployment

**Q:** If a new deployment introduces a bug, how do you roll back safely?

**A:**  
- Use blue-green or canary deployment strategies to minimize impact.
- Roll back to the previous stable version using your deployment tool.
- Ensure database migrations are reversible or have down scripts.
- Monitor system health after rollback.

---

# Advanced Troubleshooting Scenarios

---

### 1. Scenario: Memory Leak in the Application

**Q:** The ProductService starts consuming more memory over time and eventually crashes. How would you diagnose and fix this?

**A:**  
- Use JVM profiling tools (e.g., VisualVM, YourKit) to analyze heap usage and identify memory leaks.
- Check for unclosed resources (e.g., database connections, streams).
- Review code for large object retention (e.g., static collections, caches).
- Enable GC logs and analyze for frequent full GCs.
- Fix the root cause and add monitoring/alerts for memory usage.

---

### 2. Scenario: OpenSearch Index Corruption

**Q:** Product search returns inconsistent or missing results. How do you troubleshoot OpenSearch index corruption?

**A:**  
- Check OpenSearch logs for errors or warnings.
- Reindex the data from MySQL to OpenSearch.
- Validate index mappings and settings.
- Implement regular backups and test restore procedures.
- Add monitoring for index health and document counts.

---

### 3. Scenario: Slow Database Queries

**Q:** Some endpoints are slow due to database queries. How do you identify and resolve slow queries?

**A:**  
- Enable SQL query logging in `application.properties`.
- Use database tools (e.g., MySQL EXPLAIN) to analyze query plans.
- Add or optimize indexes on frequently queried columns.
- Refactor queries to reduce joins or subqueries.
- Consider denormalization or caching for hot data.

---

### 4. Scenario: Stale Data in Redis Cache

**Q:** Users see outdated product data due to stale cache entries. How do you handle cache invalidation?

**A:**  
- Implement cache eviction policies (e.g., TTL, LRU).
- Invalidate or update cache entries on product updates/deletes.
- Use cache-aside pattern to refresh cache on misses.
- Add monitoring for cache hit/miss rates.

---

### 5. Scenario: Service Unavailable Due to Eureka Outage

**Q:** If Eureka goes down, how does ProductService handle service discovery and what can you do to mitigate this?

**A:**  
- Configure Eureka client with fallback mechanisms (e.g., local cache of registry).
- Use retries and circuit breakers for service calls.
- Monitor Eureka health and set up alerts.
- Consider running multiple Eureka instances for high availability.

---

### 6. Scenario: JWT Token Expiry Issues

**Q:** Users are logged out unexpectedly due to JWT token expiry. How do you handle token refresh and session management?

**A:**  
- Implement token refresh endpoints in the authentication service.
- Use refresh tokens with short-lived access tokens.
- Handle token expiry gracefully in the frontend and prompt users to re-authenticate or refresh.

---

### 7. Scenario: Data Loss After Deployment

**Q:** After a deployment, some product data is missing. How do you investigate and prevent this?

**A:**  
- Check migration scripts for accidental data drops or destructive changes.
- Review deployment logs for errors during migration.
- Restore data from backups if needed.
- Implement migration dry-runs and backups before production deployments.

---

### 8. Scenario: API Rate Limiting

**Q:** How would you protect your ProductService API from abuse or accidental overload?

**A:**  
- Implement rate limiting using API gateway or Spring Cloud Gateway.
- Use bucket4j or similar libraries for in-app rate limiting.
- Return appropriate HTTP status codes (e.g., 429 Too Many Requests).
- Monitor and alert on rate limit breaches.

---

If you need more questions or want to tailor these for a specific role (developer, architect, SRE), let me know! 