package com.bootcamp.bc_forum2.model.dto;

import lombok.Getter;

@Getter
public class PostDto {
  private Long userId;
  private Long id;
  private String title;
  private String body;
}
