package com.ccc.quotedemo.dto;



import com.ccc.quotedemo.model.enums.StatusEnum;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {
    private Long idBooking;

    private Long idPatient;

    private Long idServices;

    private Long idDoctor;

    private LocalDateTime initDate;
    private LocalDateTime endDate;

    private StatusEnum status;
}
