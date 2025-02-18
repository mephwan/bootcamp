package com.bootcamp.bc_forum2.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class AddressDTO {
  private String street;
  private String suite;
  private String city;
  private String zipcode;
  private GeoDTO geo;
}
