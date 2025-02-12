package com.bootcamp.bc_forum.model;

import lombok.Getter;

@Getter
public class JPHComments {
  private Long postId;
  private Long id;
  private String name;
  private String email;
  private String body;
}