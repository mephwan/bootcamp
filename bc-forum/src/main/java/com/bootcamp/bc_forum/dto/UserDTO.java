package com.bootcamp.bc_forum.dto;

import java.util.List;
import com.bootcamp.bc_forum.model.Address;
import com.bootcamp.bc_forum.model.Company;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
  private Long id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;
  private List<PostDTO> posts;

}
