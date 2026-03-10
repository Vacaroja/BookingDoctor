package com.ccc.quotedemo.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class DoctorServicesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServices;
    private String name;
    private Double price;
    private Integer duration;
    private Boolean isActive = true;

    @ManyToMany(mappedBy = "services")
    private Set<DoctorEntity> doctorHas = new HashSet<>();

}
