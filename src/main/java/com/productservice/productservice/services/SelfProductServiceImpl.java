package com.productservice.productservice.services;

import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.dtos.UserDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import com.productservice.productservice.models.Product;
import com.productservice.productservice.repository.OpenSearchProductRepository;
import com.productservice.productservice.repository.ProductRepository;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Primary // NOTE 76: this service will be the primary and no need for qualifer for distinguish in
// productcontroller
@Service
public class SelfProductServiceImpl implements ProductService {
  // need to require product Repostitory
  ProductRepository productRepository;
  // NOTE 17: need the Elastic serach object also here as
  // inject the depenedency of
  OpenSearchProductRepository openSearchProductRepository;

  private RestTemplate restTemplate;

  SelfProductServiceImpl(
      ProductRepository productRepository,
      RestTemplate restTemplate,
      OpenSearchProductRepository openSearchProductRepository) {
    this.productRepository = productRepository;
    // NOTE 125: we just autowired this so that we need not required any where else of this object
    // need to create
    this.restTemplate = restTemplate;
    this.openSearchProductRepository = openSearchProductRepository;
  }

  @Override
  public GenericProductDto getProductById(String token, Long Id) throws ProductNotFoundException {
    /*  NOTE 121 UP: Comment out the code for
         Connecting Product Microservice and User Microservice
    // NOTE 75 : from db we need to fetch the data not from the fake store database
      GenericProductDto genericProductDto = new GenericProductDto();
      Optional<Product> optionalProduct =
          productRepository.findById(
              UUID.fromString("9fa06bde-3787-4199-88a3-98d8d3ccbee7")); // Any random UUID value

      Product product = optionalProduct.get();

      genericProductDto.setDescription(product.getDescription());
      genericProductDto.setTitle(product.getTitle());
      genericProductDto.setImage(product.getImage());
      genericProductDto.setCategory(product.getCategory().toString());
      return genericProductDto;
      */

    // NOTE 122 UP: intereacting User Service with Product Service
    // Steps1: call the url getProduct by id of user Microservice which is running in port 8080
    GenericProductDto genericProductDto = new GenericProductDto();
    //    RestTemplate restTemplate = new RestTemplate();
    /*
      NOTE 126 UP: this is used for for a single instance /server of userService not dynamically
        ResponseEntity<UserDto> userDtoResponseEntity = restTemplate.getForEntity("http://localhost:4040/users/1", UserDto.class);
    */

    // This is used for Dynamically getttnig all the instances of userService and based on that
    // the send will trigger to a server using client side load Balancer
    // @LoadBalancer annotation in RestTemplate.class
    // just replace spring.application.name=userservice
    ResponseEntity<UserDto> userDtoResponseEntity =
        restTemplate.getForEntity("http://userservice/users/1", UserDto.class);

    return genericProductDto;
  }

  @Override
  public List<GenericProductDto> getAllProduct() {
    return null;
  }

  @Override
  public GenericProductDto deleteProductById(Long Id) {
    return null;
  }

  @Override
  public GenericProductDto createProduct(GenericProductDto genericProductDto) {
    Product product = new Product();

    genericProductDto.setDescription(product.getDescription());
    genericProductDto.setTitle(product.getTitle());
    genericProductDto.setImage(product.getImage());
    genericProductDto.setCategory(product.getCategory().toString());

    // before we proceed to save to it first in SQL and Then AWS
    Product savedProduct = productRepository.save(product);
    //    Save to Elastic server
    openSearchProductRepository.save(product);

    return null;
  }

  @Override
  public void updateProduct() {}
}
