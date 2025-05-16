# API Gateway Interview Questions (Scenario-Based & Troubleshooting)

## Scenario-Based Questions

1. **Scenario:** You have multiple microservices (ProductService, UserService, PaymentService) behind an API Gateway. A client reports that requests to `/products/1` are returning 404 errors, but ProductService is running and accessible directly. What steps would you take to diagnose and resolve the issue?
   - **Answer:**
     - Check the API Gateway route configuration to ensure `/products/**` is correctly mapped to ProductService.
     - Verify that ProductService is registered with Eureka under the expected name (case-sensitive).
     - Confirm that the gateway is using the correct service name in its route URI (e.g., `lb://PRODUCTSERVICE`).
     - Check for typos or path mismatches in the gateway predicates.
     - Review gateway and ProductService logs for errors or misrouted requests.
     - Test the endpoint directly on ProductService to confirm it works as expected.
     - Restart the gateway if configuration changes were made but not picked up.

2. **Scenario:** The API Gateway is configured to route `/users/**` to UserService, but requests are intermittently failing with 503 Service Unavailable. What could be causing this, and how would you troubleshoot?
   - **Answer:**
     - 503 errors indicate the gateway cannot reach any healthy instance of UserService.
     - Check if UserService instances are crashing or restarting (review logs, health checks).
     - Ensure UserService is properly registered and healthy in Eureka.
     - Look for network issues or port conflicts.
     - Check if the gateway's load balancer is configured correctly.
     - Review resource usage (CPU, memory) on UserService instances.
     - Consider increasing the number of UserService instances for better availability.

3. **Scenario:** You want to add authentication to all endpoints behind the API Gateway, but only some endpoints require user authentication. How would you design the gateway configuration to support this requirement?
   - **Answer:**
     - Use gateway filters to apply authentication only to specific routes (e.g., using `Path` predicates).
     - Configure a global authentication filter, then exclude public endpoints using `negate` or custom predicates.
     - Alternatively, use a custom filter or policy to check authentication only for protected paths.
     - Document which endpoints are public and which require authentication for clarity.

4. **Scenario:** After deploying a new version of ProductService, some clients are receiving outdated responses. How can you ensure the API Gateway always routes to healthy, up-to-date service instances?
   - **Answer:**
     - Enable health checks for ProductService so Eureka only registers healthy instances.
     - Use versioned service registration if running multiple versions in parallel.
     - Ensure old instances are deregistered from Eureka after deployment.
     - Clear any gateway or client-side caches if present.
     - Use rolling deployments to minimize downtime and stale routing.

5. **Scenario:** You need to implement rate limiting for certain endpoints (e.g., `/products/search`). How would you configure this in the API Gateway?
   - **Answer:**
     - Use the built-in rate limiting filter in Spring Cloud Gateway.
     - Configure rate limiting per route in the gateway's configuration (YAML or properties).
     - Example:
       ```yaml
       spring:
         cloud:
           gateway:
             routes:
               - id: product-search
                 uri: lb://PRODUCTSERVICE
                 predicates:
                   - Path=/products/search
                 filters:
                   - name: RequestRateLimiter
                     args:
                       redis-rate-limiter.replenishRate: 10
                       redis-rate-limiter.burstCapacity: 20
       ```
     - Use Redis or another backend for distributed rate limiting if needed.

6. **Scenario:** The API Gateway must add a custom header to all outgoing requests to backend services. How would you achieve this?
   - **Answer:**
     - Use the `AddRequestHeader` filter in the gateway configuration.
     - Example:
       ```yaml
       filters:
         - AddRequestHeader=X-Custom-Header, custom-value
       ```
     - This can be applied globally or per route.

7. **Scenario:** You want to expose a single endpoint `/api/v1/products` that aggregates data from both ProductService and InventoryService. How can the API Gateway help, and what are the limitations?
   - **Answer:**
     - The gateway can route requests, but cannot aggregate data from multiple services out of the box.
     - Use a custom filter or a backend-for-frontend (BFF) service to call both services, aggregate the data, and return a combined response.
     - Limitation: Gateway is not designed for complex data aggregation; use a BFF or composite service for this purpose.

8. **Scenario:** During a load test, you notice that the API Gateway becomes a bottleneck. What strategies can you use to scale the gateway and ensure high availability?
   - **Answer:**
     - Deploy multiple instances of the API Gateway behind a load balancer.
     - Use stateless gateway configuration to allow horizontal scaling.
     - Monitor resource usage and autoscale as needed.
     - Optimize gateway filters and avoid heavy processing in the gateway.
     - Use distributed rate limiting and caching if required.

## Troubleshooting Questions

1. **Question:** A client receives a CORS error when calling an endpoint through the API Gateway. What are the possible causes, and how do you resolve them?
   - **Answer:**
     - The gateway or backend service is not configured to allow cross-origin requests.
     - Add CORS configuration in the gateway or backend service to allow the required origins, methods, and headers.
     - Ensure preflight OPTIONS requests are handled correctly.

2. **Question:** The API Gateway is returning 504 Gateway Timeout errors for some requests. What could be the reasons, and how would you debug this?
   - **Answer:**
     - The backend service is taking too long to respond or is unavailable.
     - Check backend service health and response times.
     - Increase timeout settings in the gateway if needed.
     - Review network connectivity between the gateway and backend services.

3. **Question:** Requests routed through the API Gateway are missing the `Authorization` header when they reach the backend service. What could be wrong?
   - **Answer:**
     - The gateway may be stripping or not forwarding the header.
     - Check gateway filter configuration to ensure headers are preserved.
     - Some security filters may remove sensitive headers by default; explicitly allow them if needed.

4. **Question:** Eureka dashboard shows ProductService as unavailable, but the service is running. What steps would you take to investigate?
   - **Answer:**
     - Check ProductService logs for registration errors.
     - Ensure ProductService is configured with the correct Eureka server URL.
     - Verify network connectivity between ProductService and Eureka.
     - Check for version mismatches or firewall issues.
     - Restart ProductService to force re-registration.

5. **Question:** After updating the gateway's route configuration, changes are not reflected at runtime. What could be the cause?
   - **Answer:**
     - The gateway application may need to be restarted to pick up configuration changes.
     - If using dynamic configuration (e.g., Spring Cloud Config), ensure the config server is updated and the gateway refreshes its context.
     - Check for syntax errors in the configuration file.

6. **Question:** The API Gateway is not load balancing requests evenly across service instances. What factors could affect this behavior?
   - **Answer:**
     - Sticky sessions or client-side caching may cause uneven distribution.
     - Some instances may be unhealthy or slow, causing the load balancer to avoid them.
     - Check the load balancing algorithm (default is round-robin).
     - Ensure all instances are registered and healthy in Eureka.

7. **Question:** How would you debug a scenario where the API Gateway is returning 401 Unauthorized for all requests, even with valid tokens?
   - **Answer:**
     - Check the authentication filter configuration in the gateway.
     - Ensure the token validation logic is correct and up to date.
     - Verify that the `Authorization` header is being forwarded to the backend service.
     - Review logs for authentication errors or misconfigurations.

8. **Question:** What logs and metrics would you monitor to ensure the health and performance of the API Gateway in production?
   - **Answer:**
     - Monitor request/response times, error rates, and throughput.
     - Track gateway instance health and resource usage (CPU, memory).
     - Log failed requests, timeouts, and authentication errors.
     - Use distributed tracing to follow requests across services.
     - Monitor Eureka registration and service discovery events.

---
These questions are designed to test both conceptual understanding and practical troubleshooting skills for API Gateway scenarios in microservices architectures. 