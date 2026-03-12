package com.ccc.quotedemo.service.blockouts;

import com.ccc.quotedemo.dto.ScheduleBlockoutDTO;

import java.util.List;

public interface ScheduleBlockoutService {
    List<ScheduleBlockoutDTO> getAllSchedule();
    List<ScheduleBlockoutDTO> getAllByIdDoctor(Long id);
    ScheduleBlockoutDTO getBlockOutById(Long id);
    ScheduleBlockoutDTO createBlockOut(ScheduleBlockoutDTO blockoutDTO);
    ScheduleBlockoutDTO updateBlockOut(ScheduleBlockoutDTO blockoutDTO,Long id);
    void deleteBlockOut(Long id);
}
