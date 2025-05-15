# Low-Level Design (LLD) - ProductService

## 1. Overview
This document details the main classes, interfaces, relationships, and important methods in the ProductService microservice.

## 2. Package Structure
- **controllers/**: REST API endpoints (ProductController, SearchController, ProductControllerAdvices)
- **services/**: Business logic (ProductService, FakeStoreProductService, SelfProductServiceImpl, SearchService)
- **repository/**: Data access (ProductRepository, CategoryRepository, PriceRepository, OpenSearchProductRepository)
- **models/**: JPA entities (Product, Category, Price, Order, BaseModel)
- **dtos/**: Data Transfer Objects (GenericProductDto, FakeStoreProductDtos, UserDto, ExceptionDto, SearchRequestDto)
- **security/**: Security config and JWT utilities (SpringSecurityConfig, TokenValidator, JWTObject, Role)
- **exceptions/**: Custom exceptions (ProductNotFoundException)
- **thirdPartyClients/**: Adapters for external APIs (FakeStoreClientAdaptor, FlipkartClientAdaptor, ThirdPartyInterface)
- **inheritanceRelationsInDB/**: JPA inheritance strategies (single table, joined table, table per class, mapped superclass)

## 3. Main Classes & Responsibilities

### Controllers
- **ProductController**: Handles CRUD for products, delegates to ProductService.
- **SearchController**: Handles product search requests.
- **ProductControllerAdvices**: Global exception handler for product-related errors.

### Services
- **ProductService (interface)**: Defines product operations (CRUD, search, etc.).
- **FakeStoreProductService**: Implements ProductService, integrates with FakeStore API.
- **SelfProductServiceImpl**: Implements ProductService, uses local DB.
- **SearchService**: Handles search logic, integrates with OpenSearch.

### Repositories
- **ProductRepository**: JPA repository for Product entity.
- **CategoryRepository**: JPA repository for Category entity.
- **PriceRepository**: JPA repository for Price entity.
- **OpenSearchProductRepository**: OpenSearch repository for Product documents.

### Models/Entities
- **Product**: Represents a product, with relationships to Category and Price.
- **Category**: Represents a product category.
- **Price**: Represents product pricing.
- **Order**: Represents an order (if used).
- **BaseModel**: Common fields (e.g., id, timestamps).

### DTOs
- **GenericProductDto**: Used for API responses, decouples internal model from external contract.
- **FakeStoreProductDtos**: Used for FakeStore API integration.
- **UserDto, ExceptionDto, SearchRequestDto**: Other data transfer objects.

### Security
- **SpringSecurityConfig**: Configures JWT-based security.
- **TokenValidator**: Validates JWT tokens.
- **JWTObject, Role**: JWT payload and role management.

### Third-Party Integration
- **ThirdPartyInterface**: Abstraction for external product APIs.
- **FakeStoreClientAdaptor, FlipkartClientAdaptor**: Implementations for third-party APIs.

### Inheritance Strategies
- **single table, joined table, table per class, mapped superclass**: Demonstrates JPA inheritance patterns with example entities and repositories.

## 4. Key Relationships
- **Product <-> Category**: Many-to-One (many products to one category)
- **Product <-> Price**: One-to-One
- **Service <-> Repository**: Services use repositories for data access
- **Controller <-> Service**: Controllers delegate business logic to services
- **DTOs**: Used for all API communication

## 5. Important Methods (Examples)
- `ProductController.getProductById(Long id, String token)`
- `ProductService.createProduct(GenericProductDto dto)`
- `FakeStoreProductService.getAllProduct()`
- `SearchService.searchProducts(SearchRequestDto dto)`
- `ProductRepository.findByTitle(String title)`
- `OpenSearchProductRepository.searchByKeyword(String keyword)`

## 6. Error Handling
- Centralized in `ProductControllerAdvices` using `@ExceptionHandler` and custom exceptions.

## 7. Security
- JWT validation for protected endpoints.
- Role-based access control via Spring Security.

---

For a system overview, see the HLD (High-Level Design) document. 