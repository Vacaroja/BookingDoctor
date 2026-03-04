package com.ccc.quotedemo.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDTO {

    private Long idDoctor;
    private String name;
    private String title;

    private List<ScheduleDTO> schedule;
    private Boolean available = true;
}
