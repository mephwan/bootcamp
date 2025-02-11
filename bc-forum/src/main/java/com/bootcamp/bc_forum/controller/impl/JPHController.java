package com.bootcamp.bc_forum.controller.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_forum.controller.JPHOperation;
import com.bootcamp.bc_forum.dto.PostDTO;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.model.JPHComments;
import com.bootcamp.bc_forum.model.JPHPost;
import com.bootcamp.bc_forum.model.JPHUser;
import com.bootcamp.bc_forum.service.JPHService;

@RestController
public class JPHController implements JPHOperation {
  @Autowired
  private JPHService jphService;

  @Override
  public List<UserDTO> getAllUser() {

    List<JPHUser> users = this.jphService.getJPHUsers();
    List<JPHPost> posts = this.jphService.geJPHPosts();
    List<JPHComments> comments = this.jphService.getJPHComments();

    List<UserDTO> userDTOs = new ArrayList<>();
    List<PostDTO> postDTOs = new ArrayList<>();

    for (int i = 0; i < posts.size(); i++) {
      JPHPost currentPost = posts.get(i);
      postDTOs.add(PostDTO.builder().id(currentPost.getId()).title(currentPost.getTitle()) //
      .body(currentPost.getBody()) //
      .comments(comments.stream().filter(e -> e.getPostId() == currentPost.getId()).collect(Collectors.toList()))
      .build());
    }

    for (int i = 0; i < users.size(); i++) {
      JPHUser currentUser = users.get(i);
      userDTOs.add(UserDTO.builder().id(currentUser.getId()) //
      .name(currentUser.getName()) //
      .username(currentUser.getUsername()) //
      .email(currentUser.getEmail()) //
      .address(currentUser.getAddress())//
      .phone(currentUser.getPhone()) //
      .website(currentUser.getWebsite()) //
      .company(currentUser.getCompany()) //
      .posts(postDTOs.stream().filter(e -> e.getId() == currentUser.getId()).collect(Collectors.toList())) //
      .build());
    }
    
    return userDTOs;
  }
}
