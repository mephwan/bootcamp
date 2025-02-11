package com.bootcamp.bc_calculator.dto;

import com.bootcamp.bc_calculator.model.CalOperation;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CalculateResult {
  private String x;
  private String y;
  private CalOperation operation;
  private String result;

}
