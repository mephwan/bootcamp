package com.bootcamp.bc_forum.controller.impl;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_forum.controller.JPHOperation;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.model.Comment;
import com.bootcamp.bc_forum.model.FullUserInfo;
import com.bootcamp.bc_forum.model.JPHComments;
import com.bootcamp.bc_forum.model.JPHPost;
import com.bootcamp.bc_forum.model.JPHUser;
import com.bootcamp.bc_forum.model.PostWithComment;
import com.bootcamp.bc_forum.service.JPHService;

@RestController
public class JPHController implements JPHOperation {
  @Autowired
  private JPHService jphService;

  @Override
  public List<FullUserInfo> getAllUser() {

    List<JPHUser> users = this.jphService.getJPHUsers();
    List<JPHPost> posts = this.jphService.geJPHPosts();
    List<JPHComments> comments = this.jphService.getJPHComments();

    List<PostWithComment> postswiWithComments = new LinkedList<>();
    List<JPHComments> samePostComment = new LinkedList<>();

    for (int i = 0; i < posts.size(); i++) {
      JPHPost currentPost = posts.get(i);
      for (int j = 0; j < comments.size(); j++) {
        JPHComments currentComments = comments.get(j);
        if (currentPost.getId() == currentComments.getPostId()) {
          samePostComment.add(currentComments);
        }
      }
      postswiWithComments
          .add(PostWithComment.builder().userId(currentPost.getUserId())
              .id(currentPost.getId()).title(currentPost.getTitle())
              .body(currentPost.getBody()).comments(samePostComment).build());

      samePostComment = new LinkedList<>();
    }
    List<FullUserInfo> usersFull = new LinkedList<>();
    List<PostWithComment> sameUserPosts = new LinkedList<>();
    for (int i = 0; i < users.size(); i++) {
      JPHUser currentUser = users.get(i);
      for (int j = 0; j < postswiWithComments.size(); j++) {
        if (currentUser.getId() == postswiWithComments.get(j).getUserId()) {
          sameUserPosts.add(postswiWithComments.get(j));
        }
      }
      usersFull.add(FullUserInfo.builder().id(currentUser.getId())
          .name(currentUser.getName()).username(currentUser.getUsername())
          .email(currentUser.getEmail()).address(currentUser.getAddress())
          .phone(currentUser.getPhone()).website(currentUser.getWebsite())
          .company(currentUser.getCompany()).posts(sameUserPosts).build());
      sameUserPosts = new LinkedList<>();
    }

    return usersFull;
  }

  @Override
  public UserDTO getCommentsById(Long id) {

    List<JPHUser> users = this.jphService.getJPHUsers();
    List<JPHPost> posts = this.jphService.geJPHPosts();
    List<JPHComments> comments = this.jphService.getJPHComments();

    List<PostWithComment> postswiWithComments = new LinkedList<>();
    List<JPHComments> samePostComment = new LinkedList<>();

    for (int i = 0; i < posts.size(); i++) {
      JPHPost currentPost = posts.get(i);
      for (int j = 0; j < comments.size(); j++) {
        JPHComments currentComments = comments.get(j);
        if (currentPost.getId() == currentComments.getPostId()) {
          samePostComment.add(currentComments);
        }
      }
      postswiWithComments
          .add(PostWithComment.builder().userId(currentPost.getUserId())
              .id(currentPost.getId()).title(currentPost.getTitle())
              .body(currentPost.getBody()).comments(samePostComment).build());

      samePostComment = new LinkedList<>();
    }
    List<FullUserInfo> usersFull = new LinkedList<>();
    List<PostWithComment> sameUserPosts = new LinkedList<>();
    for (int i = 0; i < users.size(); i++) {
      JPHUser currentUser = users.get(i);
      for (int j = 0; j < postswiWithComments.size(); j++) {
        if (currentUser.getId() == postswiWithComments.get(j).getUserId()) {
          sameUserPosts.add(postswiWithComments.get(j));
        }
      }
      usersFull.add(FullUserInfo.builder().id(currentUser.getId())
          .name(currentUser.getName()).username(currentUser.getUsername())
          .email(currentUser.getEmail()).address(currentUser.getAddress())
          .phone(currentUser.getPhone()).website(currentUser.getWebsite())
          .company(currentUser.getCompany()).posts(sameUserPosts).build());
      sameUserPosts = new LinkedList<>();
    }

    FullUserInfo userById = usersFull.stream().filter(e -> e.getId() == id)
        .findFirst().orElseThrow(() -> new IllegalArgumentException());

    List<Comment> commentByPost = new LinkedList<>();

    for (int i = 0; i < userById.getPosts().size(); i++) {
      PostWithComment post = userById.getPosts().get(i);
      for (int j = 0; j < post.getComments().size(); j++) {
        JPHComments currentComent = post.getComments().get(j);
        commentByPost.add(Comment.builder().name(currentComent.getName())
        .email(currentComent.getEmail())
        .body(currentComent.getBody())
        .build());
      }
    }

    return UserDTO.builder().id(userById.getId())
    .username(userById.getUsername())
    .comments(commentByPost)
    .build();
  }
}
