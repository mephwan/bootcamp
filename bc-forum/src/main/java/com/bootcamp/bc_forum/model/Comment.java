package com.bootcamp.bc_forum.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
  public class Comment {
    private String name;
    private String email;
    private String body;
  }
