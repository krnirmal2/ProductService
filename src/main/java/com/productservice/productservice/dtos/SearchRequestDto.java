package com.productservice.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
  // NOTE 91: thinks that need to pass to search Controller
  private String query;
  private int pageNumber;
  private int itemPerPage;
}
