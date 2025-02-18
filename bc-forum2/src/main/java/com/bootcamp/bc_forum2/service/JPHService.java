package com.bootcamp.bc_forum2.service;

import java.util.List;
import com.bootcamp.bc_forum2.DTO.CommentDTO;
import com.bootcamp.bc_forum2.DTO.PostDTO;
import com.bootcamp.bc_forum2.DTO.UserDTO;
import com.bootcamp.bc_forum2.model.ApiResp;
import com.bootcamp.bc_forum2.model.dto.CommentDto;
import com.bootcamp.bc_forum2.model.dto.PostDto;
import com.bootcamp.bc_forum2.model.dto.UserDto;

public interface JPHService {
  
  void getFromJPH();

  ApiResp<List<UserDTO>> getAllUsers();

  ApiResp<UserDTO> getUserById(Long id);

  ApiResp<UserDTO> updateUserById(Long id, UserDto userDto);

  ApiResp<List<PostDTO>> getAllPosts();

  ApiResp<List<PostDTO>> getPostsByUserID(Long userId);

  ApiResp<PostDTO> addPostByUserID(Long userId, PostDto postDto);

  ApiResp<String> deletePostByPostID(Long postID);

  ApiResp<List<CommentDTO>> getAllComments();

  ApiResp<List<CommentDTO>> getCommentByPostID(Long postID);

  ApiResp<CommentDTO> addCommentByPostID(Long postID, CommentDto commentDto);

  ApiResp<CommentDTO> updateCommentByCommentID(Long commentID, CommentDto commentDto);
}
