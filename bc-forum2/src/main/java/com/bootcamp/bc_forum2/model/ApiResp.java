package com.bootcamp.bc_forum2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResp<T> {
  private String code;
  private String message;
  private T body;
}
