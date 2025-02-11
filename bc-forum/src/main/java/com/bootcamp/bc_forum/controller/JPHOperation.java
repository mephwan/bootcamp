package com.bootcamp.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.bc_forum.dto.UserDTO;

public interface JPHOperation {

  @GetMapping(value = "/users")
  List<UserDTO> getAllUser();
}
