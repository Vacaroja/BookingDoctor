package com.ccc.quotedemo.model;

import com.ccc.quotedemo.model.enums.StatusEnum;
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
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBooking;

    @ManyToOne
    private PatientEntity idPatient;

    @ManyToOne
    private DoctorServicesEntity idServices;

    @ManyToOne
    private DoctorEntity idDoctor;

    private LocalDateTime initDate;
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.PENDIENTE;

}
