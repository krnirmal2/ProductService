package com.productservice.productservice.security;

import java.util.Optional;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TokenValidator {
  // NOTE 22 UP: this are the models which are common for both
  // microservices ProductService and UserService
  // this are kept in some common place to access by all
  // and this are actually service
  private RestTemplateBuilder restTemplateBuilder;

  public TokenValidator(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplateBuilder = restTemplateBuilder;
  }

  public Optional<JWTObject> validateToken(String token) {
    // this will return corresponding object other wise return null
    // and we will call it in fakeStoreProduct service for validate the token
    // before it provide the product details
    RestTemplate restTemplate = restTemplateBuilder.build();

    // NOTE 25 UP:
    // m ake an HTTP call to userservice to call the validation method
    return Optional.empty();
  }
}
