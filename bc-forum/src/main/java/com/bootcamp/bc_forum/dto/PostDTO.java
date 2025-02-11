package com.bootcamp.bc_forum.dto;

import java.util.List;
import com.bootcamp.bc_forum.model.JPHComments;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDTO {

  private Long id;
  private String title;
  private String body;
  private List<JPHComments> comments;

  public class Comments {
    private Long id;
    private String email;
    private String body;
  }
}

