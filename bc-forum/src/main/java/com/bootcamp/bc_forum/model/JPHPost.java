package com.bootcamp.bc_forum.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JPHPost {
  private Long userId;
  private Long id;
  private String title;
  private String body;
}