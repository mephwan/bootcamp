package com.bootcamp.bc_forum2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_forum2.repository.AddressRepository;
import com.bootcamp.bc_forum2.repository.CommentRepository;
import com.bootcamp.bc_forum2.repository.CompanyRepository;
import com.bootcamp.bc_forum2.repository.GeoRepository;
import com.bootcamp.bc_forum2.repository.PostRepository;
import com.bootcamp.bc_forum2.repository.UserRepository;
import com.bootcamp.bc_forum2.service.JPHService;

@Component
public class StartUpManager implements CommandLineRunner {
  @Autowired
  private JPHService jphService;

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private GeoRepository geoRepository;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private CommentRepository commentRepository;

  @Override
  public void run(String... args) throws Exception {

    this.commentRepository.deleteAll();
    this.geoRepository.deleteAll();
    this.addressRepository.deleteAll();
    this.companyRepository.deleteAll();
    this.postRepository.deleteAll();
    this.userRepository.deleteAll();
    
    this.jphService.getFromJPH();

  }
}
