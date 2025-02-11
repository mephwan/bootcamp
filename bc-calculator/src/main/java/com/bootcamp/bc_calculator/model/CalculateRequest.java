package com.bootcamp.bc_calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculateRequest {
  private String x;
  private String y;
  private CalOperation operation;  
}
