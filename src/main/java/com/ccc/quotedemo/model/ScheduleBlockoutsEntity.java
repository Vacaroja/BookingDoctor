package com.ccc.quotedemo.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ScheduleBlockoutsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBlockOut;

    @ManyToOne
    @JoinColumn(nullable = false)
    private DoctorEntity doctor;

    private LocalDateTime initDate;
    private LocalDateTime endDate;
    private String reason;
    private Boolean isActive = true;

}
