package com.ccc.quotedemo.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ScheduleBlockoutDTO {
    private Long idBlockOut;

    private Long idDoctor;

    private LocalDateTime initDate;
    private LocalDateTime endDate;
    private String reason;
}
