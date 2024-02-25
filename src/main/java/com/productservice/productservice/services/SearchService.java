package com.productservice.productservice.services;

import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.models.Product;
import com.productservice.productservice.models.SortParam;
import com.productservice.productservice.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

  // NOTE 92: need to integrate repository of product to fetch the required
  // details from db
  private ProductRepository productRepository;

  public SearchService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<GenericProductDto> searchProducts(
      String query, int pageNumber, int itemPerPage, List<SortParam> sortParams) {
    // Return list of products

    // NOTE 97: we will use the PageRequest class and
    //    PageRequest pageRequest = PageRequest.of(pageNumber, itemPerPage);

    // NOTE 101: Sort Object
    /*
     NOTE :  THIS IS HARD CODED SAMPLE SORT OBJECT BUT WE NEED FLEXIABLE ACC.
         TO CUSTOMER SENDING TO REQUEST DTO
         Sort sort = Sort.by("title").ascending()
             .and(Sort.by("rating").ascending())
             .and(Sort.by("price").descending());
    */

    Sort sort = null;

    if (sortParams.get(0).getSortType().equals("ASC")) {
      sort = Sort.by(sortParams.get(0).getSortParamName()).ascending();
    } else {
      sort = Sort.by(sortParams.get(0).getSortParamName()).descending();
    }
    for (int i = 1; i < sortParams.size(); i++) {
      if (sortParams.get(i).getSortType().equals("ASC")) {
        sort.and(Sort.by(sortParams.get(i).getSortParamName()).ascending());
      } else {
        sort.and(Sort.by(sortParams.get(i).getSortParamName()).descending());
      }
    }
    PageRequest pageRequest = PageRequest.of(pageNumber, itemPerPage, sort);

    List<Product> products = productRepository.findByTitleContaining(query, pageRequest);
    List<GenericProductDto> genericProductDtos = new ArrayList<>();

    for (Product product : products) {
      genericProductDtos.add(product.from(product));
    }
    return genericProductDtos;
  }
}
