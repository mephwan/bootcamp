package com.bootcamp.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.model.FullUserInfo;

public interface JPHOperation {

  @GetMapping(value = "/users")
  List<FullUserInfo> getAllUser();

  @GetMapping(value = "/user")
  UserDTO getCommentsById(@RequestParam Long id);
}