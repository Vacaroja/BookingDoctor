package com.ccc.quotedemo.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
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

    @ManyToMany()
    @JoinTable(name = "doctor_services", joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "services_id"))
    private Set<DoctorServicesEntity> services = new HashSet<>();
}
