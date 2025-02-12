package com.bootcamp.bc_forum.controller.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(value = BusinessException.class)
  public ErrorResp handleBusinessException(BusinessException e) {
    return new ErrorResp(e.getCode(), e.getMessage());
  }

  @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
  public ErrorResp handleMATME(MethodArgumentTypeMismatchException e) {
    return new ErrorResp(2, "Invalid Input");
  }

  @ExceptionHandler(value = HttpClientErrorException.class)
  public ErrorResp handleHttpError(HttpClientErrorException e) {
    return new ErrorResp(3, "RestTemplate Error - JsonPlaceHolder");
  }
}
