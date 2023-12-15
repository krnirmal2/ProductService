package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDtos;
import com.productservice.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService") // it means initialise the onject of ProductService
public class FakeStoreProductService implements ProductService {
    //    String getProductUrl = "https://fakestoreapi.com/products/1";
    String getAllProductUrl = "https://fakestoreapi.com/products";
    String createProductUrl = "https://fakestoreapi.com/products";
    String getProductUrl = "https://fakestoreapi.com/products/{id}";// for variable id we can just use variable in curly
    //braces
    private RestTemplateBuilder restTemplateBuilder;
    //create constructor for builder design pattern

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public FakeStoreProductDtos getProductById(Long Id) {
        // RestTemplate
        RestTemplate restTemplate = restTemplateBuilder.build();//for rest Api get/post/delete option

        // get call put url , response, variable
        ResponseEntity<FakeStoreProductDtos> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDtos.class, Id);

//       step2
        // so as we get the fakeStore product dtos as more tight couple
        // to do it more loosly coupled lets  add one more extra abstract layer
        // so that next we can change what ever next requirement come in product
        // add one more extra dtos for generic pupose and passed the product dtos
        FakeStoreProductDtos fakeStoreProductDtos = new FakeStoreProductDtos();
        convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(fakeStoreProductDtos);
        return responseEntity.getBody();
    }

    private static GenericProductDto convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(FakeStoreProductDtos fakeStoreProductDtos) {
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
        //step3
        // here we will use java generic https://www.baeldung.com/java-type-erasure
        //we need to find the all the list of product and each product having different feild so we
        // need array of FakeStoreProductDtos to collect all the list of product FakeStoreProductDtos[].class
        RestTemplate restTemplate = restTemplateBuilder.build();//for rest Api get/post/delete option

        ResponseEntity<FakeStoreProductDtos[]> responseEntity = restTemplate.getForEntity(getAllProductUrl, FakeStoreProductDtos[].class);

        List<GenericProductDto> result = new ArrayList<>();
        List<FakeStoreProductDtos> fakeStoreProductDtosList = List.of(responseEntity.getBody());
        for (FakeStoreProductDtos fakeStoreProductDto : fakeStoreProductDtosList) {
            result.add(convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(fakeStoreProductDto));
        }
        return result;

    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        // step 4
        // create the product and we will pass the parameter of requestDto(genericProductDto) and responseDtos (FakeStoreProductDtos)
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDtos> responseEntity = restTemplate.postForEntity(createProductUrl, genericProductDto, FakeStoreProductDtos.class);
        return convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(responseEntity.getBody()); // getting the body that send as json from request
    }
    @Override
    public void deleteProductById() {

    }



    @Override
    public void updateProduct() {

    }
}
