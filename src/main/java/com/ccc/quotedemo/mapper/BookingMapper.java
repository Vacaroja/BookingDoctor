package com.ccc.quotedemo.mapper;

import com.ccc.quotedemo.dto.BookingDTO;
import com.ccc.quotedemo.model.BookingEntity;
import com.ccc.quotedemo.model.DoctorEntity;
import com.ccc.quotedemo.model.DoctorServicesEntity;
import com.ccc.quotedemo.model.PatientEntity;

public class BookingMapper {
    public static BookingDTO toDTO(BookingEntity booking) {
        if (booking == null) throw new IllegalArgumentException("Booking not valid");
        return BookingDTO.builder().
                idBooking(booking.getIdBooking()).
                idPatient(booking.getIdPatient().getIdPatient()).
                idServices(booking.getIdServices().getIdServices()).
                idDoctor(booking.getIdDoctor().getIdDoctor()).
                initDate(booking.getInitDate()).
                endDate(booking.getEndDate()).
                status(booking.getStatus()).
                build();
    }
    public static BookingEntity toEntity(BookingDTO booking, PatientEntity patient, DoctorServicesEntity services, DoctorEntity doctor) {
        if (booking == null) throw new IllegalArgumentException("Booking not valid");
        return BookingEntity.builder().
                idBooking(booking.getIdBooking()).
                idPatient(patient).
                idServices(services).
                idDoctor(doctor).
                initDate(booking.getInitDate()).
                endDate(booking.getEndDate()).
                status(booking.getStatus()).
                build();
    }
}
