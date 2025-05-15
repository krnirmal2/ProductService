# ProductService - Schema, ER Diagram, and Class Communication

---

## 1. Entity-Relationship (ER) Diagram (Textual)

```
+-----------+        +-----------+        +-----------+
|  Category |<------>|  Product  |<------>|   Price   |
+-----------+   1  M +-----------+ 1   1 +-----------+
      ^                        |
      |                        |
      |                        v
      |                   +-----------+
      |                   |   Order   |
      |                   +-----------+
      |                        ^
      |                        |
      +------------------------+
```

- **Category (1) <--- (M) Product**: Many products can belong to one category.
- **Product (1) <--- (1) Price**: Each product has one price.
- **Product (1) <--- (M) Order**: Many orders can reference one product.

---

## 2. Class Relationships & Communication

### Main Classes
- **Product**: Entity representing a product. Has relationships to `Category` and `Price`.
- **Category**: Entity representing a product category.
- **Price**: Entity representing the price of a product.
- **Order**: Entity representing an order (if used).
- **DTOs**: Data Transfer Objects for API communication (e.g., `GenericProductDto`).
- **Repositories**: JPA repositories for data access (e.g., `ProductRepository`).
- **Services**: Business logic (e.g., `ProductService`, `FakeStoreProductService`).
- **Controllers**: REST endpoints (e.g., `ProductController`).

### Communication Flow

1. **Controller Layer**
   - Receives HTTP requests (e.g., create product, get product by ID).
   - Validates input and maps request data to DTOs.
   - Delegates business logic to the Service layer.

2. **Service Layer**
   - Contains business logic (e.g., validation, orchestration, integration with third-party APIs).
   - Maps between DTOs and Entities.
   - Calls Repository layer for data persistence.
   - May call external services or adaptors (e.g., FakeStore, Flipkart).

3. **Repository Layer**
   - Handles CRUD operations with the database using JPA/Hibernate.
   - Returns Entities to the Service layer.

4. **DTOs**
   - Used for all API communication to decouple internal models from external contracts.
   - Service layer maps Entities to DTOs for responses, and DTOs to Entities for requests.

5. **Third-Party Integration**
   - Service layer uses adaptor classes implementing a common interface for external APIs.
   - Adaptor classes handle API calls and map external data to internal DTOs.

6. **Exception Handling**
   - `@ControllerAdvice` classes handle exceptions thrown by controllers or services, returning meaningful error responses.

### Example Communication Sequence

- **Client** → `ProductController.createProduct()` → `ProductService.createProduct()` → `ProductRepository.save()`
- **Client** → `ProductController.getProductById()` → `ProductService.getProductById()` → `ProductRepository.findById()`
- **Client** → `ProductController.searchProducts()` → `SearchService.searchProducts()` → `OpenSearchProductRepository.searchByKeyword()`
- **Client** → `ProductController.getProductById()` → `ProductService.getProductById()` → `ThirdPartyInterface.getProductById()` (if using external API)

---

## 3. Best Practices

- **Always use DTOs** for API communication to decouple internal models from external contracts.
- **Service layer** should contain all business logic and orchestration.
- **Repositories** should only handle data access, not business logic.
- **Use the Adapter pattern** for third-party integrations to allow easy swapping or extension.
- **Centralize exception handling** with `@ControllerAdvice`.
- **Document relationships** and communication flows for maintainability.

---

If you want a graphical ER diagram, you can use tools like dbdiagram.io or draw.io based on the above schema. 