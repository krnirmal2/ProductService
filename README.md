# ProductService Project Documentation

## Table of Contents
- [ProductService Project Documentation](#productservice-project-documentation)
  - [Table of Contents](#table-of-contents)
  - [Overview](#overview)
  - [Architecture](#architecture)
  - [Main Components](#main-components)
    - [Controllers](#controllers)
    - [Services](#services)
    - [Repositories](#repositories)
    - [Models/Entities](#modelsentities)
    - [DTOs](#dtos)
    - [Security](#security)
    - [Exception Handling](#exception-handling)
    - [Third-Party Integrations](#third-party-integrations)
    - [Inheritance Strategies](#inheritance-strategies)
  - [Configuration](#configuration)
  - [Error Handling \& Solutions](#error-handling--solutions)
  - [Running the Project](#running-the-project)
  - [Testing](#testing)
  - [Notes \& Best Practices](#notes--best-practices)

---

## Overview

**ProductService** is a Spring Boot application that manages products, categories, and prices, with support for multiple inheritance strategies in JPA, security via JWT, and integration with third-party product providers (FakeStore, Flipkart, etc.).

---

## Architecture

- **Backend:** Spring Boot (Java)
- **Persistence:** JPA/Hibernate, MySQL (configurable)
- **Security:** Spring Security with JWT
- **Third-Party Integration:** REST clients for external product APIs
- **DTOs:** Used for API communication
- **Exception Handling:** Custom exceptions and global handler

---

## Main Components

### Controllers
- `ProductController`: Handles product CRUD and search endpoints.
- `ProductControllerAdvices`: Global exception handler for product-related errors.
- `SearchController`: Handles product search requests.

### Services
- `ProductService`: Interface for product operations.
- `FakeStoreProductService`, `SelfProductServiceImpl`: Implementations for product operations (local and third-party).
- `SearchService`: Handles search logic.

### Repositories
- `ProductRepository`, `CategoryRepository`, `PriceRepository`: JPA repositories for entities.
- `OpenSearchProductRepository`: For OpenSearch integration.

### Models/Entities
- `Product`, `Category`, `Price`, `Order`: Main business entities.
- Inheritance models under `inheritanceRelationsInDB` (single table, table per class, joined, mapped superclass).

### DTOs
- `GenericProductDto`, `FakeStoreProductDtos`, `UserDto`, `ExceptionDto`, `SearchRequestDto`: Data transfer objects for API requests/responses.

### Security
- `SpringSecurityConfig`: Configures JWT-based security.
- `TokenValidator`, `JWTObject`, `Role`: JWT utilities and role management.

### Exception Handling
- `ProductNotFoundException`: Custom exception for missing products.
- `ProductControllerAdvices`: Handles exceptions and returns proper API responses.

### Third-Party Integrations
- `ThirdPartyInterface`: Abstraction for external product APIs.
- `FakeStoreClientAdaptor`, `FlipkartClientAdaptor`: Implementations for FakeStore and Flipkart.

### Inheritance Strategies
- Demonstrates JPA inheritance: single table, table per class, joined, mapped superclass (see respective subfolders).

---

## Configuration

- `application.properties`: Database, JPA, and other Spring Boot settings.
- SQL scripts in `sqlQuery/SQL.sql`.

---

## Error Handling & Solutions

See `ERROR_ENCOUNTERED_AND_SOLUTION.md` for common errors and their solutions, such as:
- JPA query method naming issues.
- Bean definition conflicts (JPA vs. Elasticsearch).
- Required request headers in controllers (e.g., `Authorization`).

---

## Running the Project

1. **Build:** `./mvnw clean install`
2. **Run:** `./mvnw spring-boot:run`
3. **API Docs:** Use Postman or Swagger (if enabled) for API exploration.

---

## Testing

- Test classes are under `src/test/java/com/productservice`.
- Use `./mvnw test` to run tests.

---

## Notes & Best Practices

- Always check method names in repositories for JPA compatibility.
- For microservice communication, handle missing headers gracefully (`required = false` for `@RequestHeader`).
- Remove `@Document` from entities if not using Elasticsearch.
- Use DTOs for all API communication to decouple internal models from external contracts. 