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
  private RestTemplateBuilder restTemplateBuilder; // for connection with user service for authenticate the product

  public TokenValidator(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplateBuilder = restTemplateBuilder;
  }
  /*this method should call the userservice to validate the token , if the token is valid then return thr corresponding
  * object else return empty*/

  // this is very generic method so that where we call we got some common repsonse and then use particular from this
  public Optional<JWTObject> validateToken(String token) {
    // this will return corresponding object other wise return null
    // and we will call it in fakeStoreProduct service for validate the token
    // before it provide the product details
    RestTemplate restTemplate = restTemplateBuilder.build();
    // we have to call user service

    // NOTE 25 UP:
    // m ake an HTTP call to userservice to call the validation method
    return Optional.empty();
  }
}
