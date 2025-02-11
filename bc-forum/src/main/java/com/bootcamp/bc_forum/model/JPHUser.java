package com.bootcamp.bc_forum.model;

import lombok.Getter;

@Getter
public class JPHUser {
  private Long id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;

}
