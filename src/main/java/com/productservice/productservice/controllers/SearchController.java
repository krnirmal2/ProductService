package com.productservice.productservice.controllers;

import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.dtos.SearchRequestDto;
import com.productservice.productservice.services.SearchService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
  //    NOTE 90: implementing simple search API
  // we can fetch particular item form the db

  private SearchService searchService;

  public SearchController(SearchService searchService) {
    this.searchService = searchService;
  }

  @PostMapping
  public List<GenericProductDto> searchProducts(@RequestBody SearchRequestDto requestDto) {
    return searchService.searchProducts(
        requestDto.getQuery(), requestDto.getPageNumber(), requestDto.getItemPerPage());
  }
}
