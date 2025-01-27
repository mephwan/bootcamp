package com.bccalculator.bc_calculator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BcCalculator {
  private String x;
  private String y;
  private String operation;
  private String result;

  public BcCalculator(String x, String y, String operation) {
    this.x = x;
    this.y = y;
    this.operation = operation;
    this.result = calculateResult();
  }

  public String calculateResult() {

    Double newX = 0.0;
    Double newY = 0.0;
    CalOperation oper = null;

    try {
      newX = Double.parseDouble(this.x);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid Input");
    }
    try {
      newY = Double.parseDouble(this.y);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid Input");
    }
    try {
      oper = CalOperation.valueOf(this.operation.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid Input.");
    }

    String result = switch (oper) {
      case ADD -> String.valueOf(newX + newY);
      case SUB -> String.valueOf(newX - newY);
      case MUL -> String.valueOf(newX * newY);
      case DIV -> {
        try {
          yield String.valueOf(BigDecimal.valueOf(newX)
              .divide(BigDecimal.valueOf(newY), 5, RoundingMode.HALF_UP)
              .doubleValue());
        } catch (ArithmeticException e) {
          throw new ArithmeticException("/ by zero");
        }
      }
    };

    return result;
  }

}
