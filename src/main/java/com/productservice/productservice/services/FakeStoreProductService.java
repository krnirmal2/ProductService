package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDtos;
import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import com.productservice.productservice.thirdPartyClients.fakeStoreClient.FakeStoreClientdaptor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

// import com.productservice.productservice.thirdPartyClients.fakeStoreClient.FakeStoreAdaptor;
// import io.micrometer.core.instrument.Meter;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
@Service("fakeStoreProductService") // it means initialise the onject of ProductService
public class FakeStoreProductService implements ProductService {
  private FakeStoreClientdaptor fakeStoreClientdaptor;
  // NOTE 20:
  // we will remove the dependency of url from here as it is moved to
  // third party client class
  /*
    //    String getProductUrl = "https://fakestoreapi.com/products/1";
    String getAllProductUrl = "https://fakestoreapi.com/products";
    String createProductUrl = "https://fakestoreapi.com/products";
    String getProductUrl =
        "https://fakestoreapi.com/products/{id}"; // for variable id we can just use variable in curly
    String deleteByProductId =
        "https://fakestoreapi.com/products/{id}"; // for variable id we can just use variable in curly
    // braces
    private RestTemplateBuilder     restTemplateBuilder; // he RestTemplate class is a central class in Spring that simplifies
    // communication with HTTP servers and provides a higher-level, more
    // convenient API compared to using the standard Java HttpURLConnection.
    // It supports various HTTP methods like GET, POST, PUT, DELETE, etc.,
    // and it can handle request and response conversions using message convert
    // create constructor for builder design pattern
  */

  public FakeStoreProductService(FakeStoreClientdaptor fakeStoreClientdaptor) {
    this.fakeStoreClientdaptor = fakeStoreClientdaptor;
  }

  @Override
  public GenericProductDto getProductById(Long Id) throws ProductNotFoundException {
    /* // RestTemplate
      RestTemplate restTemplate = restTemplateBuilder.build(); // for rest Api get/post/delete option

      // get call put url , response, variable
      ResponseEntity<FakeStoreProductDtos> responseEntity =
          restTemplate.getForEntity(getProductUrl, FakeStoreProductDtos.class, Id);

      //  NOTE 2 :      step2
      // so as we get the fakeStore product dtos as more tight couple
      // to do it more loosly coupled lets  add one more extra abstract layer
      // so that next we can change what ever next requirement come in product
      // add one more extra dtos for generic pupose and passed the product dtos
      FakeStoreProductDtos fakeStoreProductDtos = new FakeStoreProductDtos();

      fakeStoreProductDtos = responseEntity.getBody();
      if (fakeStoreProductDtos == null) {
        throw new ProductNotFoundException(Id);
      }
    return convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(fakeStoreProductDtos);


      */

    /* //NOTE 21:
    // comments earilier code and use fakeStoreAdaptor class
    return  fakeStoreAdaptor.getProductById(Id);
    */
    //    NOTE 31:
    return convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(
        fakeStoreClientdaptor.getProductById(Id));
  }

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

  @Override
  public List<GenericProductDto> getAllProduct() {
    /*  // NOTE : step3
    // here we will use java generic https://www.baeldung.com/java-type-erasure
    // we need to find the all the list of product and each product having different feild so we
    // need array of FakeStoreProductDtos to collect all the list of product
    // FakeStoreProductDtos[].class
    RestTemplate restTemplate = restTemplateBuilder.build(); // for rest Api get/post/delete option

    ResponseEntity<FakeStoreProductDtos[]> responseEntity =
        restTemplate.getForEntity(getAllProductUrl, FakeStoreProductDtos[].class);

    List<GenericProductDto> result = new ArrayList<>();
    List<FakeStoreProductDtos> fakeStoreProductDtosList =
        List.of(Objects.requireNonNull(responseEntity.getBody()));
    for (FakeStoreProductDtos fakeStoreProductDto : fakeStoreProductDtosList) {
      result.add(
          convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(fakeStoreProductDto));
    }
    return result;*/

    // NOTE 22 :
    // remove earilier dependency code and use adaptor
    //    return  fakeStoreAdaptor.getAllProduct();
    // NOTE 29 :
    List<FakeStoreProductDtos> fakeStoreProductDtosList = fakeStoreClientdaptor.getAllProduct();
    List<GenericProductDto> result = new ArrayList<>();
    for (FakeStoreProductDtos fakeStoreProductDto : fakeStoreProductDtosList) {
      result.add(
          convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(fakeStoreProductDto));
    }
    return result;
  }

  @Override
  public GenericProductDto createProduct(GenericProductDto genericProductDto) {
    /* // NOTE : step 4
    // create the product and we will pass the parameter of requestDto(genericProductDto) and
    // responseDtos (FakeStoreProductDtos)
    RestTemplate restTemplate =
        restTemplateBuilder
            .build(); // TODO: BUILDER DESGIN PATTERN NOTE   THIS METHOD USE BUILDER DESIGN PATTERN
    // IN ORDER TO COMMUNICATE WITH EXTERNAL AND WE CAN SET WHAT EVER WE WANT LIKE WEB CLIENT
    ResponseEntity<FakeStoreProductDtos> responseEntity =
        restTemplate.postForEntity(createProductUrl, genericProductDto, FakeStoreProductDtos.class);
    return convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(
        responseEntity.getBody()); // getting the body that send as json from request*/

    /*
    //  NOTE 23:
    //    return fakeStoreAdaptor.createProduct(genericProductDto);

      */
    //  NOTE 30:
    return convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(
        fakeStoreClientdaptor.createProduct(genericProductDto));
  }

  @Override
  public GenericProductDto deleteProductById(Long id) {
    /*RestTemplate restTemplate = restTemplateBuilder.build();

    // NOTE :  step 5
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
    return convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(
        responseEntity.getBody());*/
    // NOTE 24:
    // similarly use adaptor
    //
    //    return fakeStoreAdaptor.deleteProductById(id);
    // NOTE 29 :
    // and convert the fakeStoreProductDtos to GenericProductDtos
    return convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(
        fakeStoreClientdaptor.deleteProductById(id));
  }

  @Override
  public void updateProduct() {}
}
