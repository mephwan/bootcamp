package com.bootcamp.bc_forum2.service.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.bc_forum2.DTO.AddressDTO;
import com.bootcamp.bc_forum2.DTO.CommentDTO;
import com.bootcamp.bc_forum2.DTO.CompanyDTO;
import com.bootcamp.bc_forum2.DTO.GeoDTO;
import com.bootcamp.bc_forum2.DTO.PostDTO;
import com.bootcamp.bc_forum2.DTO.UserDTO;
import com.bootcamp.bc_forum2.DTO.mapper.DTOmapper;
import com.bootcamp.bc_forum2.entity.AddressEntity;
import com.bootcamp.bc_forum2.entity.CommentEntity;
import com.bootcamp.bc_forum2.entity.CompanyEntity;
import com.bootcamp.bc_forum2.entity.GeoEntity;
import com.bootcamp.bc_forum2.entity.PostEntity;
import com.bootcamp.bc_forum2.entity.UserEntity;
import com.bootcamp.bc_forum2.exception.BusinessException;
import com.bootcamp.bc_forum2.lib.Scheme;
import com.bootcamp.bc_forum2.model.ApiResp;
import com.bootcamp.bc_forum2.model.dto.CommentDto;
import com.bootcamp.bc_forum2.model.dto.PostDto;
import com.bootcamp.bc_forum2.model.dto.UserDto;
import com.bootcamp.bc_forum2.model.dto.mapper.EntityMapper;
import com.bootcamp.bc_forum2.repository.AddressRepository;
import com.bootcamp.bc_forum2.repository.CommentRepository;
import com.bootcamp.bc_forum2.repository.CompanyRepository;
import com.bootcamp.bc_forum2.repository.GeoRepository;
import com.bootcamp.bc_forum2.repository.PostRepository;
import com.bootcamp.bc_forum2.repository.UserRepository;
import com.bootcamp.bc_forum2.service.JPHService;

@Service
public class JPHServiceImpl implements JPHService {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private EntityMapper entityMapper;

  @Autowired
  private DTOmapper dtoMapper;

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

  @Value("${api.jph.domain}")
  private String domain;

  @Value("${api.jph.users.endpoint}")
  private String userEndpoint;

  @Value("${api.jph.posts.endpoint}")
  private String postsEndpoint;

  @Value("${api.jph.comments.endpoint}")
  private String commentsEndpoint;

  @Override
  public void getFromJPH() {
    String userURL =
        UriComponentsBuilder.newInstance().scheme(Scheme.HTTPS.name())
            .host(domain).path(userEndpoint).build().toUriString();

    List<UserDto> userDtos =
        Arrays.asList(this.restTemplate.getForObject(userURL, UserDto[].class));

    String PostURL =
        UriComponentsBuilder.newInstance().scheme(Scheme.HTTPS.name())
            .host(domain).path(postsEndpoint).build().toUriString();

    List<PostDto> postDtos =
        Arrays.asList(this.restTemplate.getForObject(PostURL, PostDto[].class));

    String commentURL =
        UriComponentsBuilder.newInstance().scheme(Scheme.HTTPS.name())
            .host(domain).path(commentsEndpoint).build().toUriString();

    List<CommentDto> commentDtos = Arrays
        .asList(this.restTemplate.getForObject(commentURL, CommentDto[].class));

    for (UserDto userDto : userDtos) {
      UserEntity userEntity = this.entityMapper.map(userDto);
      AddressEntity addressEntity = this.entityMapper.map(userDto.getAddress());
      addressEntity.setUserEntity(userEntity);
      CompanyEntity companyEntity = this.entityMapper.map(userDto.getCompany());
      companyEntity.setUserEntity(userEntity);
      GeoEntity geoEntity =
          this.entityMapper.map(userDto.getAddress().getGeo());
      geoEntity.setAddressEntity(addressEntity);

      userRepository.save(userEntity);
      addressRepository.save(addressEntity);
      companyRepository.save(companyEntity);
      geoRepository.save(geoEntity);

      List<PostDto> matchedPosts =
          postDtos.stream().filter(e -> e.getUserId().equals(userDto.getId()))
              .collect(Collectors.toList());

      for (PostDto matchedPost : matchedPosts) {
        PostEntity postEntity = this.entityMapper.map(matchedPost);
        postEntity.setUserEntity(userEntity);
        postRepository.save(postEntity);

        List<CommentDto> matchedComments = commentDtos.stream()
            .filter(e -> e.getPostId().equals(matchedPost.getId()))
            .collect(Collectors.toList());

        for (CommentDto matchedComment : matchedComments) {
          CommentEntity commentEntity = this.entityMapper.map(matchedComment);
          commentEntity.setPostEntity(postEntity);

          commentRepository.save(commentEntity);
        }
      }
    }
  }

  @Override
  public ApiResp<List<UserDTO>> getAllUsers() {
    List<UserDTO> userDTOs = new LinkedList<>();
    List<UserEntity> userEntities = userRepository.findAll();

    for (UserEntity userEntity : userEntities) {
      UserDTO userDTO = this.dtoMapper.map(userEntity);
      AddressEntity addressEntity = addressRepository.findAll().stream().filter(e -> e.getUserEntity().getId().equals(userEntity.getId())).findFirst().orElseThrow(() -> new BusinessException("99991", "ID not found"));
      AddressDTO addressDTO = this.dtoMapper.map(addressEntity);
      GeoEntity geoEntity = geoRepository.findAll().stream().filter(e -> e.getAddressEntity().getUserEntity().getId().equals(userEntity.getId())).findFirst().orElseThrow(() -> new BusinessException("99991", "ID not found"));
      GeoDTO geoDTO = this.dtoMapper.map(geoEntity);

      CompanyEntity companyEntity = companyRepository.findAll().stream().filter(e -> e.getUserEntity().getId().equals(userEntity.getId())).findFirst().orElseThrow(() -> new BusinessException("99991", "ID not found"));
      CompanyDTO companyDTO = this.dtoMapper.map(companyEntity);
      
      addressDTO.setGeo(geoDTO);
      userDTO.setAddress(addressDTO);
      userDTO.setCompany(companyDTO);

      List<PostEntity> postEntities = postRepository.findAll();
      
      List<PostDTO> postDTOs = postEntities.stream().filter(e -> e.getUserEntity().getId().equals(userEntity.getId())).map(e -> this.dtoMapper.map(e)).collect(Collectors.toList());

      for (PostDTO postDTO : postDTOs) {

        List<CommentEntity> commentEntities = commentRepository.findAll();
        List<CommentDTO> commentDTOs = commentEntities.stream().filter(e -> e.getPostEntity().getId().equals(postDTO.getId())).map(e -> this.dtoMapper.map(e)).collect(Collectors.toList());
        postDTO.setComments(commentDTOs);

      }

      userDTO.setPosts(postDTOs);

      userDTOs.add(userDTO);
    }

    return new ApiResp<>("000000", "Success.", userDTOs);
  }

  @Override
  public ApiResp<UserDTO> getUserById(Long id) {
    UserEntity userEntity = this.userRepository.findById(id).orElseThrow(() -> new BusinessException("99991", "ID not found"));
    UserDTO userDTO = this.dtoMapper.map(userEntity);

    AddressEntity addressEntity = addressRepository.findAll().stream().filter(e -> e.getUserEntity().getId().equals(userEntity.getId())).findFirst().orElseThrow(() -> new BusinessException("99991", "ID not found"));
    AddressDTO addressDTO = this.dtoMapper.map(addressEntity);

    GeoEntity geoEntity = geoRepository.findAll().stream().filter(e -> e.getAddressEntity().getUserEntity().getId().equals(userEntity.getId())).findFirst().orElseThrow(() -> new BusinessException("99991", "ID not found"));
    GeoDTO geoDTO = this.dtoMapper.map(geoEntity);

    CompanyEntity companyEntity = companyRepository.findAll().stream().filter(e -> e.getUserEntity().getId().equals(userEntity.getId())).findFirst().orElseThrow(() -> new BusinessException("99991", "ID not found"));
    CompanyDTO companyDTO = this.dtoMapper.map(companyEntity);

    List<PostEntity> postEntities = postRepository.findAll().stream().filter(e -> e.getUserEntity().getId().equals(userEntity.getId())).collect(Collectors.toList());
    List<PostDTO> postDTOs = new LinkedList<>();
    for (PostEntity postEntity : postEntities) {
      PostDTO postDTO = this.dtoMapper.map(postEntity);

      List<CommentDTO> commentDTOs = commentRepository.findAll().stream().filter(e -> e.getPostEntity().getId().equals(postEntity.getId())).map(e -> this.dtoMapper.map(e)).collect(Collectors.toList());

      postDTO.setComments(commentDTOs);
      postDTOs.add(postDTO);
    }
    
    addressDTO.setGeo(geoDTO);
    userDTO.setAddress(addressDTO);
    userDTO.setCompany(companyDTO);
    userDTO.setPosts(postDTOs);

    return new ApiResp<UserDTO>("000000", "Success.", userDTO);
  }

  @Override
  public ApiResp<UserDTO> updateUserById(Long id, UserDto userDto) {
    UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new BusinessException("999991", "ID not found"));
    AddressEntity addressEntity = addressRepository.findAll().stream().filter(e -> e.getUserEntity().getId().equals(id)).findFirst().orElseThrow(() -> new BusinessException("999991", "ID not found"));
    GeoEntity geoEntity = geoRepository.findAll().stream().filter(e -> e.getAddressEntity().getUserEntity().getId().equals(userEntity.getId())).findFirst().orElseThrow(() -> new BusinessException("999991", "ID not found"));
    CompanyEntity companyEntity = companyRepository.findAll().stream().filter(e -> e.getUserEntity().getId().equals(userEntity.getId())).findFirst().orElseThrow(() -> new BusinessException("999991", "ID not found"));
    
    userEntity.setName(userDto.getName());
    userEntity.setUsername(userDto.getUsername());
    userEntity.setEmail(userDto.getEmail());
    userEntity.setPhone(userDto.getPhone());
    userEntity.setWebsite(userDto.getWebsite());

    addressEntity.setStreet(userDto.getAddress().getStreet());
    addressEntity.setSuite(userDto.getAddress().getSuite());
    addressEntity.setCity(userDto.getAddress().getCity());
    addressEntity.setZipcode(userDto.getAddress().getZipcode());

    geoEntity.setLat(userDto.getAddress().getGeo().getLat());
    geoEntity.setLng(userDto.getAddress().getGeo().getLng());

    companyEntity.setName(userDto.getCompany().getName());
    companyEntity.setCatchPhrase(userDto.getCompany().getCatchPhrase());
    companyEntity.setBs(userDto.getCompany().getBs());

    userRepository.save(userEntity);
    addressRepository.save(addressEntity);
    geoRepository.save(geoEntity);
    companyRepository.save(companyEntity);

    UserDTO userDTO = this.dtoMapper.map(userEntity);
    userDTO.setAddress(this.dtoMapper.map(addressEntity));
    userDTO.setCompany(this.dtoMapper.map(companyEntity));
    
    return new ApiResp<UserDTO>("000000", "Success.", userDTO);
  }

  @Override
  public ApiResp<List<PostDTO>> getAllPosts() {
    List<PostDTO> postDTOs = postRepository.findAll().stream().map(e -> this.dtoMapper.map(e)).collect(Collectors.toList());
    for (PostDTO postDTO : postDTOs) {
      List<CommentDTO> commentDTOs = commentRepository.findAll().stream().filter(e -> e.getPostEntity().getId().equals(postDTO.getId())).map(e -> this.dtoMapper.map(e)).collect(Collectors.toList());
      postDTO.setComments(commentDTOs);
    }

    return new ApiResp<List<PostDTO>>("000000", "Success.", postDTOs);
  }

  @Override
  public ApiResp<List<PostDTO>> getPostsByUserID(Long userId) {
    UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new BusinessException("999991", "ID not found"));

    List<PostDTO> postDTOs = postRepository.findAll().stream().filter(e -> e.getUserEntity().getId().equals(userEntity.getId())).map(e -> this.dtoMapper.map(e)).collect(Collectors.toList());
    
    for (PostDTO postDTO : postDTOs) {
      List<CommentDTO> commentDTOs = commentRepository.findAll().stream().filter(e -> e.getPostEntity().getId().equals(postDTO.getId())).map(e -> this.dtoMapper.map(e)).collect(Collectors.toList());

      postDTO.setComments(commentDTOs);
    }
    
    return new ApiResp<List<PostDTO>>("000000", "Success.", postDTOs);
  }

  @Override
  public ApiResp<PostDTO> addPostByUserID(Long userId, PostDto postDto) {
    PostEntity postEntity = this.entityMapper.map(postDto);
    UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new BusinessException("999991", "ID not found"));
    postEntity.setUserEntity(userEntity);
    postRepository.save(postEntity);

    return new ApiResp<PostDTO>("000000", "Success.", this.dtoMapper.map(postEntity));
  }

  @Override
  public ApiResp<String> deletePostByPostID(Long postID) {
    PostEntity postEntity = postRepository.findById(postID).orElseThrow(() -> new BusinessException("999991", "ID not found"));
    postRepository.delete(postEntity);
    return new ApiResp<String>("000000", "Success.", "Post was deleted ");
  }

  @Override
  public ApiResp<List<CommentDTO>> getAllComments() {
    return new ApiResp<List<CommentDTO>>("000000", "Success.", commentRepository.findAll().stream().map(e -> this.dtoMapper.map(e)).collect(Collectors.toList()));
  }

  @Override
  public ApiResp<List<CommentDTO>> getCommentByPostID(Long postID) {
    return new ApiResp<List<CommentDTO>>("000000", "Success.", commentRepository.findAll().stream().filter(e -> e.getPostEntity().getId().equals(postID)).map(e -> this.dtoMapper.map(e)).collect(Collectors.toList()));
  }

  @Override
  public ApiResp<CommentDTO> addCommentByPostID(Long postID, CommentDto commentDto) {
    PostEntity postEntity = postRepository.findAll().stream().filter(e -> e.getId().equals(postID)).findFirst().orElseThrow(() -> new BusinessException("999991", "ID not found"));
    CommentEntity commentEntity = this.entityMapper.map(commentDto);
    commentEntity.setPostEntity(postEntity);
    commentRepository.save(commentEntity);

    return new ApiResp<CommentDTO>("000000", "Success.", this.dtoMapper.map(commentEntity));
  }

  @Override
  public ApiResp<CommentDTO> updateCommentByCommentID(Long commentID, CommentDto commentDto) {
    CommentEntity commentEntity = commentRepository.findById(commentID).orElseThrow(() -> new BusinessException("99993", "Comment ID not found"));
    commentEntity.setName(commentDto.getName());
    commentEntity.setEmail(commentDto.getEmail());
    commentEntity.setBody(commentDto.getBody());
    commentRepository.save(commentEntity);
    return new ApiResp<CommentDTO>("000000", "Success.", this.dtoMapper.map(commentEntity));
  }
}
