package com.productservice.productservice.controllers;

import com.productservice.productservice.dtos.ExceptionDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice // it is in between controller and dispatcher
// it will handle the Exception and send the request dispatcher for client notification
// it will triggered when there is some exception happend

public class ProductControllerAdvices {
  // NOTE:15
  // this class used specially to handle what ever the exception is coming in the
  // ProductController class will handle by this
  // LIKE DIPACTCHER( as dispatcher help to map the incoming and outgoing request ) from
  // in controller
  //

  @ExceptionHandler(ProductNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND) // this will change the status code of the exception
  @ResponseBody() // this will help to give what we just send value in the body not other traces
  private ExceptionDto handleProductNotFoundException(
      ProductNotFoundException productNotFoundException) {
    // NOTE 16 :
    // this is Another way of handle Execption as we did in the ProductController class

    ExceptionDto exceptionDto = new ExceptionDto();
    exceptionDto.setMessage(productNotFoundException.getMessage());
    exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);

    // NOTE 17:
    //  we can use @ResponseStatus(HttpStatus.NOT_FOUND)
    // instead of ResponseEntity
    ResponseEntity responseEntity = new ResponseEntity(exceptionDto, HttpStatus.NOT_FOUND);
    //          return responseEntity(exceptionDto,HttpStatus.NOT_FOUND)
    return exceptionDto;
  }
}
