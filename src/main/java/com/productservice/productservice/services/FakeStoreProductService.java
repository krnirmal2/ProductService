package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDtos;
import com.productservice.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService") // it means initialise the onject of ProductService
public class FakeStoreProductService implements ProductService{
    String getProductUrl = "https://fakestoreapi.com/products/1";
    private RestTemplateBuilder restTemplateBuilder;
    //create constructor for builder design pattern


    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public FakeStoreProductDtos getProductById(Long Id) {
        // RestTemplate

        RestTemplate restTemplate = restTemplateBuilder.build();
        // get call put url , response, variable
       ResponseEntity<FakeStoreProductDtos> responseEntity =  restTemplate.getForEntity(getProductUrl, FakeStoreProductDtos.class);


//       step2
       // so as we get the fakeStore product dtos as more tight couple
        // to do it more loosly coupled lets  add one more extra abstract layer
        // so that next we can change what ever next requirement come in product
       // add one more extra dtos for generic pupose and passed the product dtos
        FakeStoreProductDtos fakeStoreProductDtos = new FakeStoreProductDtos();
        convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(fakeStoreProductDtos);



        return responseEntity.getBody();
    }

    private static GenericProductDto convertFakeStoreProductDtoToGenericProductDtoForAbstractionLayer(FakeStoreProductDtos fakeStoreProductDtos){
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
    public void getAllProduct() {

    }

    @Override
    public void deleteProductById() {

    }

    @Override
    public void createProduct() {

    }

    @Override
    public void updateProduct() {

    }
}
