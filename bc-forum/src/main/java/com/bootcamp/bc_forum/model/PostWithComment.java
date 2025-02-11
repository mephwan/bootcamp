package com.bootcamp.bc_forum.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostWithComment {
  private Long userId;
  private Long id;
  private String title;
  private String body;

  private List<JPHComments> comments;
}
