package com.bootcamp.bc_forum.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResp {
  private Integer code;
  private String message;
}
