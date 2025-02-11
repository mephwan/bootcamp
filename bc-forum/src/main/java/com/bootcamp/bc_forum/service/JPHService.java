package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.model.JPHComments;
import com.bootcamp.bc_forum.model.JPHPost;
import com.bootcamp.bc_forum.model.JPHUser;

public interface JPHService {
  
  List<JPHUser> getJPHUsers();

  List<JPHPost> geJPHPosts();

  List<JPHComments> getJPHComments();
  
}
