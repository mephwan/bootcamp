package com.bootcamp.bc_calculator.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_calculator.dto.CalculateResult;
import com.bootcamp.bc_calculator.model.CalculateRequest;
import com.bootcamp.bc_calculator.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

  @Override
  public CalculateResult calculate(CalculateRequest calculateRequest) {
     Double x = Double.parseDouble(calculateRequest.getX());
     Double y = Double.parseDouble(calculateRequest.getY());

    String result = switch (calculateRequest.getOperation()) {
      case ADD -> String.valueOf(BigDecimal.valueOf(x).add(BigDecimal.valueOf(y)).doubleValue());
      case SUB -> String.valueOf(BigDecimal.valueOf(x).subtract(BigDecimal.valueOf(y)).doubleValue());
      case MUL -> String.valueOf(BigDecimal.valueOf(x).multiply(BigDecimal.valueOf(y)).doubleValue());
      case DIV -> String.valueOf(BigDecimal.valueOf(x).divide(BigDecimal.valueOf(y), 5, RoundingMode.HALF_UP).doubleValue());
    };

    return CalculateResult.builder() //
        .x(calculateRequest.getX()) //
        .y(calculateRequest.getY()) //
        .operation(calculateRequest.getOperation()) //
        .result(result) //
        .build();
  }
}
