package com.bootcamp.bc_calculator.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_calculator.controller.CalculatorOperation;
import com.bootcamp.bc_calculator.dto.CalculateResult;
import com.bootcamp.bc_calculator.model.CalOperation;
import com.bootcamp.bc_calculator.model.CalculateRequest;
import com.bootcamp.bc_calculator.service.impl.CalculatorServiceImpl;

@RestController
public class CalculatorController implements CalculatorOperation {
  
  @Autowired
  private CalculatorServiceImpl calculatorServiceImpl;

  @Override
  public CalculateResult calculateByParam(String x, String y, CalOperation operation) {
    CalculateRequest calculateRequest = new CalculateRequest(x, y, operation);
    return calculatorServiceImpl.calculate(calculateRequest);
  }

  @Override
  public CalculateResult calculate(CalculateRequest calculateRequest) {
    return calculatorServiceImpl.calculate(calculateRequest);
  }

  @Override
  public CalculateResult calculateByPath(String x, String y, CalOperation operation) {
    CalculateRequest calculateRequest = new CalculateRequest(x, y, operation);
    return calculatorServiceImpl.calculate(calculateRequest);
  }
}
