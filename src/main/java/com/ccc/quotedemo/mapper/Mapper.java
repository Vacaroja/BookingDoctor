package com.ccc.quotedemo.mapper;

import com.ccc.quotedemo.dto.DoctorDTO;
import com.ccc.quotedemo.dto.PatientDTO;
import com.ccc.quotedemo.dto.ScheduleDTO;
import com.ccc.quotedemo.dto.ServicesDTO;
import com.ccc.quotedemo.model.DoctorEntity;
import com.ccc.quotedemo.model.DoctorServicesEntity;
import com.ccc.quotedemo.model.PatientEntity;
import com.ccc.quotedemo.model.ScheduleEntity;

import java.util.List;

public class Mapper {

    //DoctorEntity to DoctorDTO.

    public static DoctorDTO toSummaryDto(DoctorEntity doctor, Boolean isAvaliable) {
        if (doctor == null) return null;


        return DoctorDTO.builder().
                idDoctor(doctor.getIdDoctor()).
                name(doctor.getName()).
                title(doctor.getTitle()).
                available(isAvaliable).
                schedule(null).
                build();


    }

    public static DoctorDTO toDto(DoctorEntity doctor, Boolean isAvaliable) {
        if (doctor == null) return null;


        List<ScheduleDTO> scheduleList = (doctor.getSchedules() != null)
                ? doctor.getSchedules().stream().map(Mapper::toDto).toList()
                : List.of();
        List<ServicesDTO> services = (doctor.getServices() != null)
                ? doctor.getServices().stream().map(Mapper::toDto).toList()
                : List.of();


        return DoctorDTO.builder().
                idDoctor(doctor.getIdDoctor()).
                name(doctor.getName()).
                title(doctor.getTitle()).
                available(isAvaliable).
                services(services).
                schedule(scheduleList).
                build();


    }

    public static DoctorEntity toEntity(DoctorDTO dto) {
        if (dto == null) return null;

        return DoctorEntity.builder().
                idDoctor(dto.getIdDoctor()).
                name(dto.getName()).
                title(dto.getTitle()).
                isActive(true)
                .build();


    }

    public static ScheduleDTO toDto(ScheduleEntity schedule) {
        if (schedule == null) return null;
        return ScheduleDTO.builder().
                idSchedule(schedule.getIdSchedule()).
                idDoctor(schedule.getIdDoctor().getIdDoctor()).
                initDate(schedule.getInitDate()).
                endDate(schedule.getEndDate()).
                dayOfWeek(schedule.getDayOfWeek())
                .build();
    }

    public static ServicesDTO toDto(DoctorServicesEntity services) {
        if (services == null) return null;
        return ServicesDTO.builder()
                .idServices(services.getIdServices())
                .name(services.getName())
                .price(services.getPrice())
                .duration(services.getDuration())
                .build();

    }
    public static DoctorServicesEntity toEntity(ServicesDTO services) {
        if (services == null) return null;
        return DoctorServicesEntity.builder()
                .idServices(services.getIdServices())
                .name(services.getName())
                .price(services.getPrice())
                .duration(services.getDuration())
                .isActive(true)
                .build();

    }

    public static PatientDTO toDto(PatientEntity patient) {
        return PatientDTO.builder()
                .idPatient(patient.getIdPatient())
                .name(patient.getName())
                .age(patient.getAge())
                .PhoneNumber(patient.getPhoneNumber())
                .isActive(patient.getIsActive())
                .build();
    }

    public static PatientEntity toEntity(PatientDTO patient) {
        return PatientEntity.builder()
                .idPatient(patient.getIdPatient())
                .name(patient.getName())
                .age(patient.getAge()).
                phoneNumber(patient.getPhoneNumber())
                .isActive(patient.getIsActive())
                .build();
    }
}

