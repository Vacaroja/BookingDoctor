package com.ccc.quotedemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServicesDTO {
    private Long idServices;
    private String name;
    private Double price;
    private Integer duration;
}
