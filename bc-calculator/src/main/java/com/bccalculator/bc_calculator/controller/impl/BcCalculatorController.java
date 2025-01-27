package com.bccalculator.bc_calculator.controller.impl;

import org.springframework.web.bind.annotation.RestController;
import com.bccalculator.bc_calculator.controller.BcCalculatorOperation;
import com.bccalculator.bc_calculator.model.BcCalculator;

@RestController

public class BcCalculatorController implements BcCalculatorOperation {
  
  @Override
  public BcCalculator calculateByRequestParam(String x, String y, String operation){
    return new BcCalculator(x, y, operation);
  }

  @Override
  public BcCalculator calculateByRequestBody(BcCalculator requestCal) {

    return requestCal;
  }

  @Override
  public BcCalculator calculateByPathVariable(String x, String y, String operation) {
    return new BcCalculator(x, y, operation);
    
  }
}
