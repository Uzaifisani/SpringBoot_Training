package com.FeignClientDemo.FeignClientDemo.dto;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
}
