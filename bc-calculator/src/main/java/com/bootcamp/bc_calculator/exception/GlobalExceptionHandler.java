package com.bootcamp.bc_calculator.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class GlobalExceptionHandler{

  @ExceptionHandler(value = NumberFormatException.class)
  public ErrorResp handleNFE(NumberFormatException e) {
    return new ErrorResp(9, "Invalid Input.");
  }

  @ExceptionHandler(value = InvalidFormatException.class)
  public ErrorResp handleIFE(InvalidFormatException e) {
    return new ErrorResp(9, "Invalid Input.");
  }
  
  @ExceptionHandler(value = ArithmeticException.class)
  public ErrorResp handleAE(ArithmeticException e) {
    return new ErrorResp(9, "/ by zero");
  }
}
