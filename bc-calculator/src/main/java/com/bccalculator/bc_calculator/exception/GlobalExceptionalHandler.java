package com.bccalculator.bc_calculator.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bccalculator.bc_calculator.model.ApiResp;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionalHandler {

  @ExceptionHandler(value = ArithmeticException.class)
  public ApiResp<Void> handleIAE(ArithmeticException e) {
    return new ApiResp<Void>(9, e.getMessage());
  }

  @ExceptionHandler(value = NumberFormatException.class)
  public ApiResp<Void> handleIAE(NumberFormatException e) {
    return new ApiResp<Void>(9, e.getMessage());
  }

  @ExceptionHandler(value = IllegalArgumentException.class)
  public ApiResp<Void> handleIAE(IllegalArgumentException e) {
    return new ApiResp<Void>(9, e.getMessage());
  }
}
