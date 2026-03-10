package com.ccc.quotedemo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DoctorServicesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServices;
    private String name;
    private Double price;
    private Integer duration;
    private Boolean isActive = true;

    @ManyToMany(mappedBy = "services")
    private Set<DoctorEntity> doctorHas;

}
