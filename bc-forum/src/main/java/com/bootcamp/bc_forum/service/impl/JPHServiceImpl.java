package com.bootcamp.bc_forum.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.bc_forum.lib.Scheme;
import com.bootcamp.bc_forum.model.JPHComments;
import com.bootcamp.bc_forum.model.JPHPost;
import com.bootcamp.bc_forum.model.JPHUser;
import com.bootcamp.bc_forum.service.JPHService;

@Service
public class JPHServiceImpl implements JPHService {

  @Value("${api.jph.domain}")
  private String domain;

  @Value("${api.jph.users.endpoint}")
  private String userEndpoint;

  @Value("${api.jph.posts.endpoint}")
  private String postsEndpoint;

  @Value("${api.jph.comments.endpoint}")
  private String commentsEndpoint;

  @Autowired
  private RestTemplate restTemplate;

  public List<JPHUser> getJPHUsers() {
    String url = UriComponentsBuilder.newInstance().scheme(Scheme.HTTPS.name()).host(domain).path(userEndpoint).build().toUriString();
    JPHUser[] users = this.restTemplate.getForObject(url, JPHUser[].class);
    return Arrays.asList(users);
  }

  public List<JPHPost> geJPHPosts() {
    String url = UriComponentsBuilder.newInstance().scheme(Scheme.HTTPS.name()).host(domain).path(postsEndpoint).build().toUriString();
    JPHPost[] posts = this.restTemplate.getForObject(url, JPHPost[].class);
    return Arrays.asList(posts);
  }

  public List<JPHComments> getJPHComments() {
    String url = UriComponentsBuilder.newInstance().scheme(Scheme.HTTPS.name()).host(domain).path(commentsEndpoint).build().toUriString();
    JPHComments[] comments = this.restTemplate.getForObject(url, JPHComments[].class);
    return Arrays.asList(comments);
  }
}