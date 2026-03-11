package com.ccc.quotedemo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSchedule;

    @ManyToOne
    @JoinColumn(name = "id_doctor",nullable = false)
    private DoctorEntity idDoctor;

    private LocalDateTime initDate;
    private LocalDateTime endDate;
    private Integer dayOfWeek;
    private Boolean isActive = true;
}
