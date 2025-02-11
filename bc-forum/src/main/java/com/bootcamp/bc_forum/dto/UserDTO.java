package com.bootcamp.bc_forum.dto;

import java.util.List;
import com.bootcamp.bc_forum.model.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
  private Long id;
  private String username;
  private List<Comment> comments;

}