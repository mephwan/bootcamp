package com.bootcamp.bc_forum2.DTO;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class PostDTO {
  private Long id;
  private String title;
  private String body;
  private List<CommentDTO> comments;
}
