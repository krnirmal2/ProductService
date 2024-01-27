package com.productservice.productservice.thirdPartyClients;

/*
NOTE 32: we have to remove this interface as
// we need not required this any more
// and here each function will use GenericProductDto But fakeStoreAdaptor
// will return FakeStoreProductDto
//


import java.util.List;

public interface ThirdPartyInterface {
    //NOTE 18 : Adapter Design Pattern Implementation
    // we will Refactor our code to reduce the extnesiblity
    // and reduce the dependency
    // What ever the method is in ProductService interface
    // same methods will be also present in this
    public GenericProductDto getProductById(Long Id) throws ProductNotFoundException;

    public List<GenericProductDto> getAllProduct();

    public GenericProductDto deleteProductById(Long Id);

    public GenericProductDto createProduct(GenericProductDto genericProductDto);

    public void updateProduct();


}
*/
