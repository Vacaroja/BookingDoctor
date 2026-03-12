package com.ccc.quotedemo.mapper;


import com.ccc.quotedemo.dto.ScheduleBlockoutDTO;
import com.ccc.quotedemo.model.DoctorEntity;
import com.ccc.quotedemo.model.ScheduleBlockoutsEntity;

public class BlockOutsMapper {
    public static ScheduleBlockoutDTO toDto(ScheduleBlockoutsEntity blockout) {
        if (blockout == null) throw new IllegalArgumentException("Schedule Blockout or Id not valid");
        return ScheduleBlockoutDTO.builder().
                idBlockOut(blockout.getIdBlockOut()).
                idDoctor(blockout.getDoctor().getIdDoctor()).
                initDate(blockout.getInitDate()).
                endDate(blockout.getEndDate()).
                reason(blockout.getReason()).
                build();

    }
    public static ScheduleBlockoutsEntity toEntity(ScheduleBlockoutDTO blockout, DoctorEntity doctor) {
        if (blockout == null) throw new IllegalArgumentException("Schedule Blockout or Id not valid");
        return ScheduleBlockoutsEntity.builder().
                idBlockOut(blockout.getIdBlockOut()).
                doctor(doctor).
                initDate(blockout.getInitDate()).
                endDate(blockout.getEndDate()).
                reason(blockout.getReason()).
                isActive(true).
                build();

    }
}
