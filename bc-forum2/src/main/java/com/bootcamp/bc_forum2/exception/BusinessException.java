package com.bootcamp.bc_forum2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BusinessException extends RuntimeException {
  private String code;
  private String message;

}
