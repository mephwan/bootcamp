package com.bootcamp.bc_forum2.model.dto;

import lombok.Getter;

@Getter
public class CommentDto {
  private Long postId;
  private Long id;
  private String name;
  private String email;
  private String body;

}
