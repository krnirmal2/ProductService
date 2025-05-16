# Redis Integration in ProductService

## What is Redis?
Redis is an open-source, in-memory data structure store, used as a database, cache, and message broker. It is known for its high performance and is commonly used to cache frequently accessed data to improve application speed and scalability.

## Why Redis in ProductService?
- **Performance:** Caching with Redis reduces database load and improves response times for frequently requested data.
- **Scalability:** Helps the service handle more requests efficiently.
- **Simplicity:** Easy to integrate with Spring Boot using starter dependencies.

## Configuration
Redis is configured in `src/main/resources/application.properties`:

```
spring.redis.host=localhost
spring.redis.port=6379
```

Ensure these properties match your local or production Redis instance.

## Setup: Installing and Running Redis Locally
1. **Download and Install Redis:**
   - Visit [Redis Downloads](https://redis.io/docs/install/install-redis/) and follow the instructions for your OS.
   - For Windows, you may use [Memurai](https://www.memurai.com/) or [Redis for Windows](https://github.com/microsoftarchive/redis/releases) as official support is limited.
2. **Start Redis Server:**
   - Run `redis-server` in your terminal or use the provided executable.
3. **Verify Connection:**
   - Use `redis-cli ping` to check if the server is running (should return `PONG`).

## How Redis is Used in ProductService
- Redis is used as a cache layer for frequently accessed data (e.g., product details, search results).
- Spring Boot auto-configures Redis connections using the properties above.
- Annotate service methods with `@Cacheable`, `@CachePut`, or `@CacheEvict` to leverage caching.
- Example (not actual code):
  ```java
  @Cacheable(value = "products", key = "#id")
  public Product getProductById(Long id) { ... }
  ```

## Where Redis is Used in ProductService

Redis is directly integrated and used in the following files and classes:

### 1. `FakeStoreProductService.java`
- **Location:** `src/main/java/com/productservice/productservice/services/FakeStoreProductService.java`
- **How Redis is Used:**
  - The class uses a `RedisTemplate<String, FakeStoreProductDtos>` to interact with Redis.
  - In the `getProductById(String authToken, Long id)` method:
    - It first attempts to retrieve product data from Redis using `redisTemplate.opsForHash().get("PRODUCTS", id)`.
    - If the product is not found in the cache (cache miss), it fetches the product from the third-party API, then stores the result in Redis with `redisTemplate.opsForHash().put("PRODUCTS", id, fakeStoreProductDto)`.
    - This approach reduces load on the third-party API and improves response time for repeated requests.

### 2. `FakeStoreProductDtos.java`
- **Location:** `src/main/java/com/productservice/productservice/dtos/FakeStoreProductDtos.java`
- **How Redis is Used:**
  - This DTO is used as the value type for Redis caching.
  - The class implements `Serializable` to ensure objects can be stored in Redis.

**Note:**
Currently, Redis is only used for caching product details in the `FakeStoreProductService` implementation. Other service implementations (like `SelfProductServiceImpl`) do not use Redis.

## Production Tips
- Use a managed Redis service (e.g., AWS ElastiCache, Azure Cache for Redis) for high availability.
- Secure your Redis instance (require authentication, restrict network access).
- Monitor cache hit/miss rates and memory usage.

## Troubleshooting
- **Connection Refused:** Ensure Redis server is running and accessible at the configured host/port.
- **Data Not Cached:** Check if caching annotations are present and cache manager is configured.
- **Performance Issues:** Monitor Redis memory and evictions; consider increasing resources or tuning eviction policies.

## References
- [Spring Boot Redis Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data.nosql.redis)
- [Redis Official Documentation](https://redis.io/docs/)

---
For further details, see `application.properties` and relevant service classes using caching annotations. 