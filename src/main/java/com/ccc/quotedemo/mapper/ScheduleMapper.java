package com.ccc.quotedemo.mapper;

import com.ccc.quotedemo.dto.ScheduleDTO;
import com.ccc.quotedemo.model.DoctorEntity;
import com.ccc.quotedemo.model.ScheduleEntity;

public class ScheduleMapper {
    public static ScheduleDTO toDto(ScheduleEntity services) {
        if (services == null) return null;
        return ScheduleDTO.builder().
                idSchedule(services.getIdSchedule()).
                idDoctor(services.getIdDoctor().getIdDoctor()).
                initDate(services.getInitDate()).
                endDate(services.getEndDate()).
                dayOfWeek(services.getDayOfWeek()).
                build();

    }
    public static ScheduleEntity toEntity(ScheduleDTO services, DoctorEntity doctor) {
        if (services == null) return null;
        return ScheduleEntity.builder().
                idSchedule(services.getIdSchedule()).
                idDoctor(doctor).
                initDate(services.getInitDate()).
                endDate(services.getEndDate()).
                dayOfWeek(services.getDayOfWeek()).
                isActive(true).
                build();

    }
}
