package com.ccc.quotedemo.service.schedule;


import com.ccc.quotedemo.dto.ScheduleDTO;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDTO> getSchedule();
    List<ScheduleDTO> getScheduleByDoctor(Long id);
    ScheduleDTO getScheduleById(Long id);
    ScheduleDTO createSchedule(ScheduleDTO schedule);
    ScheduleDTO updateSchedule(Long id,ScheduleDTO schedule);
    void deleteSchedule(Long id);
}
