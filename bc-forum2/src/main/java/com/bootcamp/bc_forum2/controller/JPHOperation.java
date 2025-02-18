package com.bootcamp.bc_forum2.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.bc_forum2.DTO.CommentDTO;
import com.bootcamp.bc_forum2.DTO.PostDTO;
import com.bootcamp.bc_forum2.DTO.UserDTO;
import com.bootcamp.bc_forum2.model.ApiResp;
import com.bootcamp.bc_forum2.model.dto.CommentDto;
import com.bootcamp.bc_forum2.model.dto.PostDto;
import com.bootcamp.bc_forum2.model.dto.UserDto;

public interface JPHOperation {
  
  @GetMapping(value = "/users")
  ApiResp<List<UserDTO>> getAllusers();

  @GetMapping(value = "/user/id")
  ApiResp<UserDTO> getUserById(@RequestParam Long id);

  @PutMapping(value = "/user/id/update")
  ApiResp<UserDTO> updateUserById(@RequestParam Long id, @RequestBody UserDto userDto);

  @GetMapping(value = "/posts")
  ApiResp<List<PostDTO>> getAllPosts();

  @GetMapping(value = "/posts/id/{userId}")
  ApiResp<List<PostDTO>> getPostByUserId(@PathVariable Long userId);

  @PostMapping(value = "/post/id/add/{id}")
  ApiResp<PostDTO> addPostByUserID(@PathVariable Long id, @RequestBody PostDto postDto);

  @DeleteMapping(value = "/post/delete/post_id/{postID}")
  ApiResp<String> deletePostByPostID(@PathVariable Long postID);

  @GetMapping(value = "/comments")
  ApiResp<List<CommentDTO>> getAllComments();

  @GetMapping(value = "/comments/post_id/")
  ApiResp<List<CommentDTO>> getCommentByPostID(@RequestParam Long postID);

  @PostMapping(value = "/comments/add")
  ApiResp<CommentDTO> addCommentByPostID(@RequestParam Long postID, @RequestBody CommentDto commentDto);

  @PatchMapping(value = "/comments/update/id/")
  ApiResp<CommentDTO> updateCommentByCommentID(@RequestParam Long commentID, @RequestBody CommentDto commentDto);
}
