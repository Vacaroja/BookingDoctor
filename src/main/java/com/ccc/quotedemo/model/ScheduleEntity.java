package com.ccc.quotedemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSchedule;

    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private DoctorEntity idDoctor;

    private LocalDateTime initDate;
    private LocalDateTime endDate;
    private Integer dayOfWeek;
}
