package com.productservice.productservice.thirdPartyClients.fakeStoreClient;

import com.productservice.productservice.dtos.FakeStoreProductDtos;
import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
// import com.productservice.productservice.thirdPartyClients.ThirdPartyInterface;
import java.util.List;
import java.util.Objects;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClientdaptor { // NOTE 33: remove the ThirdPartyInterface and all
  // @overide annotattion And Rename it
  // This class use for call FackeStoreProductService call
  // NOTE 19:

  // we have to implment the method of thirdparty interface (Act as BankApi)
  // we will use this to call the Api call
  // we just copy the FakeStoreProductService method as it is
  // as it was earilier we have implemented
  // Also we have to return FakeStoreProductDtos not GenericProductDto
  // as GenericProductDto is our internal implementation
  // so we will change all the return type from GenericProductDto to FakeStoreProductDtos and do the
  // necessay steps
  // here we will remove all the  url mapping

  String getAllProductUrl = "https://fakestoreapi.com/products";
  String createProductUrl = "https://fakestoreapi.com/products";
  String getProductUrl =
      "https://fakestoreapi.com/products/{id}"; // for variable id we can just use variable in curly
  String deleteByProductId =
      "https://fakestoreapi.com/products/{id}"; // for variable id we can just use variable in curly
  // braces
  private RestTemplateBuilder
      restTemplateBuilder; // he RestTemplate class is a central class in Spring that simplifies
  // communication with HTTP servers and provides a higher-level, more
  // convenient API compared to using the standard Java HttpURLConnection.
  // It supports various HTTP methods like GET, POST, PUT, DELETE, etc.,
  // and it can handle request and response conversions using message convert
  // create constructor for builder design pattern

  public FakeStoreClientdaptor(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplateBuilder = restTemplateBuilder;
  }

  //    @Override
  public FakeStoreProductDtos getProductById(Long Id) throws ProductNotFoundException {
    // RestTemplate
    RestTemplate restTemplate = restTemplateBuilder.build(); // for rest Api get/post/delete option

    // get call put url , response, variable
    ResponseEntity<FakeStoreProductDtos> responseEntity =
        restTemplate.getForEntity(getProductUrl, FakeStoreProductDtos.class, Id);

    // so as we get the fakeStore product dtos as more tight couple
    // to do it more loosly coupled lets  add one more extra abstract layer
    // so that next we can change what ever next requirement come in product
    // add one more extra dtos for generic pupose and passed the product dtos
    FakeStoreProductDtos fakeStoreProductDtos = new FakeStoreProductDtos();

    fakeStoreProductDtos = responseEntity.getBody();
    if (fakeStoreProductDtos == null) {
      throw new ProductNotFoundException(Id);
    }
    //        return
    // convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(fakeStoreProductDtos);

    // NOTE 25 :
    // return fakeStoreProductDtos because our client is not
    // responsible for Internal implementation of conversion
    // after Refactor it should directly return below
    return fakeStoreProductDtos;
  }

  // UTILIty function for converting
  private static GenericProductDto convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(
      FakeStoreProductDtos fakeStoreProductDtos) {
    GenericProductDto genericProductDto = new GenericProductDto();
    genericProductDto.setId(fakeStoreProductDtos.getId());
    genericProductDto.setDescription(fakeStoreProductDtos.getDescription());
    genericProductDto.setTitle(fakeStoreProductDtos.getTitle());
    genericProductDto.setCategory(fakeStoreProductDtos.getCategory());
    genericProductDto.setPrice(fakeStoreProductDtos.getPrice());
    genericProductDto.setImage(fakeStoreProductDtos.getImage());

    return genericProductDto;
  }

  //    @Override
  public List<FakeStoreProductDtos> getAllProduct() {

    // here we will use java generic https://www.baeldung.com/java-type-erasure
    // we need to find the all the list of product and each product having different feild so we
    // need array of FakeStoreProductDtos to collect all the list of product
    // FakeStoreProductDtos[].class
    RestTemplate restTemplate = restTemplateBuilder.build(); // for rest Api get/post/delete option

    ResponseEntity<FakeStoreProductDtos[]> responseEntity =
        restTemplate.getForEntity(getAllProductUrl, FakeStoreProductDtos[].class);

    //        List<FakeStoreProductDtos> result = new ArrayList<>();
    //        List<FakeStoreProductDtos> fakeStoreProductDtosList =
    //                List.of(Objects.requireNonNull(responseEntity.getBody()));
    //        for (FakeStoreProductDtos fakeStoreProductDto : fakeStoreProductDtosList) {
    //            result.add(
    //
    // convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(fakeStoreProductDto));
    //        }
    // NOTE 26 :
    return List.of(Objects.requireNonNull(responseEntity.getBody()));
  }

  //    @Override
  public FakeStoreProductDtos createProduct(GenericProductDto genericProductDto) {

    // create the product and we will pass the parameter of requestDto(genericProductDto) and
    // responseDtos (FakeStoreProductDtos)
    RestTemplate restTemplate =
        restTemplateBuilder
            .build(); // TODO: BUILDER DESGIN PATTERN NOTE   THIS METHOD USE BUILDER DESIGN PATTERN
    // IN ORDER TO COMMUNICATE WITH EXTERNAL AND WE CAN SET WHAT EVER WE WANT LIKE WEB CLIENT
    ResponseEntity<FakeStoreProductDtos> responseEntity =
        restTemplate.postForEntity(createProductUrl, genericProductDto, FakeStoreProductDtos.class);
    //        return convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(
    //                responseEntity.getBody()); // getting the body that send as json from request
    // NOTE 27 :
    return responseEntity.getBody();
  }

  //    @Override
  public FakeStoreProductDtos deleteProductById(Long id) {
    RestTemplate restTemplate = restTemplateBuilder.build();

    // we have changed the getForEntity inbuild method to delete the item because we have
    // return the FakeStoreProductService object to response ,
    RequestCallback requestCallback =
        restTemplate.acceptHeaderRequestCallback(FakeStoreProductDtos.class);
    ResponseExtractor<ResponseEntity<FakeStoreProductDtos>> responseExtractor =
        restTemplate.responseEntityExtractor(FakeStoreProductDtos.class);
    ResponseEntity<FakeStoreProductDtos> responseEntity =
        restTemplate.execute(
            deleteByProductId, HttpMethod.DELETE, requestCallback, responseExtractor, id);

    // convert the responseEntity to type genericProductDto
    //        return convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(
    //                responseEntity.getBody());
    // NOTE 28 :
    return responseEntity.getBody();
  }

  //    @Override
  public void updateProduct() {}
}
