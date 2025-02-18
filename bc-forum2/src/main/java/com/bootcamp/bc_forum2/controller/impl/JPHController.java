package com.bootcamp.bc_forum2.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_forum2.DTO.CommentDTO;
import com.bootcamp.bc_forum2.DTO.PostDTO;
import com.bootcamp.bc_forum2.DTO.UserDTO;
import com.bootcamp.bc_forum2.controller.JPHOperation;
import com.bootcamp.bc_forum2.model.ApiResp;
import com.bootcamp.bc_forum2.model.dto.CommentDto;
import com.bootcamp.bc_forum2.model.dto.PostDto;
import com.bootcamp.bc_forum2.model.dto.UserDto;
import com.bootcamp.bc_forum2.service.JPHService;

@RestController
public class JPHController implements JPHOperation {
  @Autowired
  private JPHService jphService;

  @Override
  public ApiResp<List<UserDTO>> getAllusers() {
    return this.jphService.getAllUsers();
  }

  @Override
  public ApiResp<UserDTO> getUserById(Long id) {
    return this.jphService.getUserById(id);
  }

  @Override
  public ApiResp<UserDTO> updateUserById(Long id, UserDto userDto) {
    return this.jphService.updateUserById(id, userDto);
  }

  @Override
  public ApiResp<List<PostDTO>> getAllPosts() {
    return this.jphService.getAllPosts();
  }

  @Override
  public ApiResp<List<PostDTO>> getPostByUserId(Long userId) {
    return this.jphService.getPostsByUserID(userId);
  }

  @Override
  public ApiResp<PostDTO> addPostByUserID(Long id, PostDto postDto) {
    return this.jphService.addPostByUserID(id, postDto);
  }

  @Override
  public ApiResp<String> deletePostByPostID(Long postID) {
    return this.jphService.deletePostByPostID(postID);
  }

  @Override
  public ApiResp<List<CommentDTO>> getAllComments() {
    return this.jphService.getAllComments();
  }

  @Override
  public ApiResp<List<CommentDTO>> getCommentByPostID(Long postID) {
    return this.jphService.getCommentByPostID(postID);
  }

  @Override
  public ApiResp<CommentDTO> addCommentByPostID(Long postID, CommentDto commentDto) {
    return this.jphService.addCommentByPostID(postID, commentDto);
  }

  @Override
  public ApiResp<CommentDTO> updateCommentByCommentID(Long commentID, CommentDto commentDto) {
    return this.jphService.updateCommentByCommentID(commentID, commentDto);
  }
}

