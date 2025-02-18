package com.bootcamp.bc_forum2.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyDTO {
  private String name;
  private String catchPhrase;
  private String bs;
}
