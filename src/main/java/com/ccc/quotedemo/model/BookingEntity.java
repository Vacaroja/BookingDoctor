package com.ccc.quotedemo.model;

import com.ccc.quotedemo.model.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
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
    private StatusEnum status = StatusEnum.PENDING;

    private Boolean isActive = true;

}
