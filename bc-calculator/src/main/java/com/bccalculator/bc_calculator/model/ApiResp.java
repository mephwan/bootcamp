package com.bccalculator.bc_calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResp<T> {
  private int code;
  private String message;
}
