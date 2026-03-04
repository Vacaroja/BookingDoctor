package com.ccc.quotedemo.dto;

import com.ccc.quotedemo.model.DoctorEntity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleBlockoutDTO {
    private Long idServices;

    private Long idDoctor;

    private LocalDate initDate;
    private LocalDate endDate;
    private String reason;
}
