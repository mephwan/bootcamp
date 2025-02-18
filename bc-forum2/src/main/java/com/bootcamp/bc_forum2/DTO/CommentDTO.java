package com.bootcamp.bc_forum2.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDTO {
  private Long id;
  private String name;
  private String email;
  private String body;
}
