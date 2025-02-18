package com.bootcamp.bc_forum2.exception;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import com.bootcamp.bc_forum2.model.ApiResp;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(value = BusinessException.class)
  public ApiResp<ArrayList> handleBusinessException(BusinessException e) {
    return new ApiResp<>(e.getCode(), e.getMessage(), new ArrayList<>());
  }

  @ExceptionHandler(value = HttpClientErrorException.class)
  public ApiResp<ArrayList> handleHttpClientErrorException(HttpClientErrorException e) {
    return new ApiResp<>("999998", "Json PlaceHolder API unavaliable", new ArrayList<>());
  }
}
