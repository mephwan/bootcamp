package com.bootcamp.bc_forum2.DTO;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GeoDTO {
  private String lat;
  private String lng;
}
