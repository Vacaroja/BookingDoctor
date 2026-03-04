package com.ccc.quotedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicesDTO {
    private Long idServices;
    private String name;
    private Double price;
    private Integer duration;
}
