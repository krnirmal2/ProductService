package com.productservice.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {
  //    NOTE 99:
  //    all the sort filter parameters from client with
  //    name of the parameter and in which way it should be
  //    sort Ascending or descending
  private String sortParamName;
  private String sortType; // Asc or Desc
}
