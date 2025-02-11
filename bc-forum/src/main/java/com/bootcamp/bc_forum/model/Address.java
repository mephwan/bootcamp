package com.bootcamp.bc_forum.model;

import lombok.Getter;

@Getter
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Getter
    public class Geo {
      private String lat;
      private String lng;
    }
  }

