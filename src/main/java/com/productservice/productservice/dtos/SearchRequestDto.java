package com.productservice.productservice.dtos;

import com.productservice.productservice.models.SortParam;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
  // NOTE 91: thinks that need to pass to search Controller
  private String query;
  private int pageNumber;
  private int itemPerPage;

  // NOTE 98: Sorting parameters comming from client to sort data
  // in ascending or descending order
  List<SortParam> sortParams;
}
