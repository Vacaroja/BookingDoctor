package com.ccc.quotedemo.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoctor;
    private String name;
    private String title;
    private Boolean isActive = true;

    @OneToMany(mappedBy = "idDoctor")
    private List<ScheduleEntity> schedules;
}
