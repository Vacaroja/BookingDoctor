package com.ccc.quotedemo.dto;


import com.ccc.quotedemo.model.DoctorEntity;
import com.ccc.quotedemo.model.DoctorServicesEntity;
import com.ccc.quotedemo.model.PatientEntity;
import com.ccc.quotedemo.model.enums.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long idBooking;

    private Long idPatient;

    private Long idServices;

    private Long idDoctor;

    private LocalDateTime initDate;
    private LocalDateTime endDate;

    private StatusEnum status;
}
