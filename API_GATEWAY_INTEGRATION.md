# API Gateway Integration in ProductService

## What is an API Gateway?
An API Gateway is a server that acts as a single entry point for client requests to multiple backend microservices. It handles routing, load balancing, authentication, and more, simplifying client interactions and centralizing cross-cutting concerns.

## Why Use an API Gateway?
- **Centralized Routing:** All requests from clients go through the gateway, which forwards them to the appropriate microservice.
- **Load Balancing:** Distributes requests across multiple instances of a service.
- **Security:** Can enforce authentication, rate limiting, and other policies in one place.
- **Service Discovery:** Integrates with Eureka to dynamically route to available services.

## How ProductService Integrates with API Gateway

### 1. Service Registration with Eureka
ProductService registers itself with the Eureka Service Discovery server. This is configured in `src/main/resources/application.properties`:

```
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
spring.application.name=ProductService
```

### 2. API Gateway Configuration
The API Gateway is implemented in the `ApiGatewayAndLoadBalancerMicroService` project. It uses Spring Cloud Gateway and is configured to route requests to services registered in Eureka.

Example configuration in `ApiGatewayAndLoadBalancerMicroService/src/main/resources/application.properties`:
```
spring.application.name=api-gateway
server.port=8080
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
# Example route (uncomment and adapt for ProductService):
# spring.cloud.gateway.routes[0].id=product-service
# spring.cloud.gateway.routes[0].uri=lb://PRODUCTSERVICE
# spring.cloud.gateway.routes[0].predicates[0]=Path=/products/**
```
- The `lb://PRODUCTSERVICE` URI tells the gateway to use load balancing and service discovery for ProductService.
- The `Path=/products/**` predicate means all requests starting with `/products/` are routed to ProductService.

### 3. How Requests are Routed
- Clients send requests to the API Gateway (e.g., `http://localhost:8080/products/1`).
- The gateway forwards the request to the appropriate instance of ProductService, discovered via Eureka.
- ProductService handles the request and returns the response through the gateway.

### 4. Where in ProductService Code
- **Controller:** `src/main/java/com/productservice/productservice/controllers/ProductController.java`
  - All endpoints under `/products` are accessible via the gateway.
  - Example:
    ```java
    @RestController
    @RequestMapping("/products")
    public class ProductController {
        // ...
        @GetMapping("/{id}")
        public GenericProductDto getProductById(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token, @PathVariable("id") Long id) {
            System.out.println("call routing this server by Api gateWAy");
            return productService.getProductById(token, id);
        }
        // ...
    }
    ```
  - The log message `call routing this server by Api gateWAy` confirms requests are routed via the gateway.

## Summary
- ProductService is registered with Eureka for service discovery.
- API Gateway (on port 8080) routes requests to ProductService using path-based routing and load balancing.
- All `/products` endpoints are accessible via the gateway, centralizing access and enabling scalable, secure microservice communication.

## Technical Documentation

### Architecture Overview
```
[Client]
   |
   v
[API Gateway (Spring Cloud Gateway, port 8080)]
   |
   v
[Eureka Service Discovery]
   |
   v
[ProductService (multiple instances possible)]
```
- The API Gateway acts as the single entry point for all client requests.
- Eureka Service Discovery keeps track of all running instances of ProductService.
- The API Gateway uses Eureka to discover and load balance requests to ProductService.

### Request Flow
1. **Client** sends a request to the API Gateway (e.g., `GET http://localhost:8080/products/1`).
2. **API Gateway** checks its route configuration and matches `/products/**` to ProductService.
3. **API Gateway** queries **Eureka** for available instances of ProductService.
4. **API Gateway** forwards the request to one of the ProductService instances (load balanced).
5. **ProductService** processes the request and returns the response to the API Gateway.
6. **API Gateway** sends the response back to the client.

### Example Route Configuration (YAML)
If you use `application.yml` instead of `application.properties` in the API Gateway:
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: product-service
          uri: lb://PRODUCTSERVICE
          predicates:
            - Path=/products/**
```

### Service Naming
- The value for `uri: lb://PRODUCTSERVICE` must match the `spring.application.name` in ProductService (case-insensitive).
- All ProductService instances must register with Eureka using the same name.

### Security and Headers
- The API Gateway can be configured to add, remove, or forward headers (e.g., authentication tokens) to ProductService.
- In `ProductController`, the `Authorization` header is forwarded and used for authentication/authorization.

### Troubleshooting
- **Service Not Found:** Ensure ProductService is running and registered with Eureka. Check the Eureka dashboard at `http://localhost:8761`.
- **Route Not Working:** Verify the route configuration in the API Gateway matches the path and service name.
- **Load Balancing Issues:** Make sure multiple instances of ProductService are running and registered with Eureka.
- **CORS Issues:** Configure CORS in the API Gateway or ProductService as needed.

### References
- [Spring Cloud Gateway Documentation](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/)
- [Spring Cloud Eureka Documentation](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html)

---
For more details, see the API Gateway's `