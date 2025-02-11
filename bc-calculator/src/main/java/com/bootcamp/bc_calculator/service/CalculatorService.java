package com.bootcamp.bc_calculator.service;

import com.bootcamp.bc_calculator.dto.CalculateResult;
import com.bootcamp.bc_calculator.model.CalculateRequest;

public interface CalculatorService {
  
  CalculateResult calculate(CalculateRequest calculateRequest);
}
