package com.productservice.productservice.controllers.Config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
  // NOTE 124: This class is used for mapping different
  // url or microservices instances for balancing the load
  @Bean
  @LoadBalanced // Act as client side loadBalancer and radomly call any instance or server of
  // UserService
  public RestTemplate getRestTemplateConfig() {
    return new RestTemplate();
  }
}
