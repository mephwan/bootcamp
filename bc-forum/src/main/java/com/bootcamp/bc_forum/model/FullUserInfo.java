package com.bootcamp.bc_forum.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FullUserInfo {
  private Long id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;

  private List<PostWithComment> posts;

}
