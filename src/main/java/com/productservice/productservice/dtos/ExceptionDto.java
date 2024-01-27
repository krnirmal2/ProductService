package com.productservice.productservice.dtos;

// NOTE 12:
// create this class to handle different excetpion
// we shall return on dtos we will give some http errror status to the user
// to make is easier to understand

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDto {
  private HttpStatus httpStatus;
  private String message;
}
