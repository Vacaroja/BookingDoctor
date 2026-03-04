package com.ccc.quotedemo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDTO {

    private Long idSchedule;

    private Long idDoctor;

    private LocalDateTime initDate;
    private LocalDateTime endDate;
    private Integer dayOfWeek;
}
