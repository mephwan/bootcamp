package com.bootcamp.bc_forum2.model.dto.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.bc_forum2.entity.AddressEntity;
import com.bootcamp.bc_forum2.entity.CommentEntity;
import com.bootcamp.bc_forum2.entity.CompanyEntity;
import com.bootcamp.bc_forum2.entity.GeoEntity;
import com.bootcamp.bc_forum2.entity.PostEntity;
import com.bootcamp.bc_forum2.entity.UserEntity;
import com.bootcamp.bc_forum2.model.dto.CommentDto;
import com.bootcamp.bc_forum2.model.dto.PostDto;
import com.bootcamp.bc_forum2.model.dto.UserDto;

@Component
public class EntityMapper {
  
  public UserEntity map(UserDto dto) {
    return UserEntity.builder().name(dto.getName()).username(dto.getUsername())
    .email(dto.getEmail()).phone(dto.getPhone()).website(dto.getWebsite()).build();
  }

  public AddressEntity map(UserDto.Address dto) {
    return AddressEntity.builder().street(dto.getStreet()).suite(dto.getSuite())
    .city(dto.getCity()).zipcode(dto.getZipcode()).build();
  }

  public CompanyEntity map(UserDto.Company dto) {
    return CompanyEntity.builder().name(dto.getName()).catchPhrase(dto.getCatchPhrase())
    .bs(dto.getBs()).build();
  }

  public GeoEntity map(UserDto.Address.Geo dto) {
    return GeoEntity.builder().lat(dto.getLat()).lng(dto.getLng()).build();
  }

  public PostEntity map(PostDto dto) {
    return PostEntity.builder().title(dto.getTitle()).body(dto.getBody()).build();
  }

  public CommentEntity map(CommentDto dto) {
    return CommentEntity.builder().name(dto.getName()).email(dto.getEmail())
    .body(dto.getBody()).build();
  }
}
