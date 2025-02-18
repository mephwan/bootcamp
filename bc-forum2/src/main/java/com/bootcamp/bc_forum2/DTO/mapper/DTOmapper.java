package com.bootcamp.bc_forum2.DTO.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.bc_forum2.DTO.AddressDTO;
import com.bootcamp.bc_forum2.DTO.CommentDTO;
import com.bootcamp.bc_forum2.DTO.CompanyDTO;
import com.bootcamp.bc_forum2.DTO.GeoDTO;
import com.bootcamp.bc_forum2.DTO.PostDTO;
import com.bootcamp.bc_forum2.DTO.UserDTO;
import com.bootcamp.bc_forum2.entity.AddressEntity;
import com.bootcamp.bc_forum2.entity.CommentEntity;
import com.bootcamp.bc_forum2.entity.CompanyEntity;
import com.bootcamp.bc_forum2.entity.GeoEntity;
import com.bootcamp.bc_forum2.entity.PostEntity;
import com.bootcamp.bc_forum2.entity.UserEntity;

@Component
public class DTOmapper {
  
  public UserDTO map(UserEntity userEntity) {
    return UserDTO.builder().id(userEntity.getId()).name(userEntity.getName())
    .username(userEntity.getUsername()).email(userEntity.getEmail())
    .phone(userEntity.getPhone()).website(userEntity.getWebsite())
    .build();
  }

  public AddressDTO map(AddressEntity addressEntity) {
    return AddressDTO.builder().street(addressEntity.getStreet())
    .suite(addressEntity.getSuite()).city(addressEntity.getCity())
    .zipcode(addressEntity.getZipcode()).build();
  }

  public GeoDTO map(GeoEntity geoEntity) {
    return GeoDTO.builder().lat(geoEntity.getLat()).lng(geoEntity.getLng()).build();
  }

  public CompanyDTO map(CompanyEntity companyEntity) {
    return CompanyDTO.builder().name(companyEntity.getName())
    .catchPhrase(companyEntity.getCatchPhrase()).bs(companyEntity.getBs())
    .build();
  }

  public PostDTO map(PostEntity postEntity) {
    return PostDTO.builder().id(postEntity.getId()).title(postEntity.getTitle())
    .body(postEntity.getBody()).build();
  }

  public CommentDTO map(CommentEntity commentEntity) {
    return CommentDTO.builder().id(commentEntity.getId()).name(commentEntity.getName())
    .email(commentEntity.getEmail()).body(commentEntity.getBody()).build();
  }
}
