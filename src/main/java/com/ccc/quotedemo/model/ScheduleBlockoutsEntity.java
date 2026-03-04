package com.ccc.quotedemo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ScheduleBlockoutsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServices;

    @ManyToOne
    private DoctorEntity doctor;

    private LocalDate initDate;
    private LocalDate endDate;
    private String reason;

}
