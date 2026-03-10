package com.ccc.quotedemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDTO {
    private Long idPatient;
    private String name;
    private Integer age;
    private String PhoneNumber;
    private Boolean isActive;
}
