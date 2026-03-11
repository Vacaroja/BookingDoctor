package com.ccc.quotedemo.service.schedule;

import com.ccc.quotedemo.dto.ScheduleDTO;
import com.ccc.quotedemo.exception.NotFoundException;
import com.ccc.quotedemo.mapper.Mapper;
import com.ccc.quotedemo.mapper.ScheduleMapper;
import com.ccc.quotedemo.model.DoctorEntity;
import com.ccc.quotedemo.model.ScheduleEntity;
import com.ccc.quotedemo.repository.DoctorRepository;
import com.ccc.quotedemo.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleImpl implements ScheduleService{

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<ScheduleDTO> getSchedule() {
        List<ScheduleEntity> schedule = scheduleRepository.findByIsActiveTrue();
        return schedule.stream().map(ScheduleMapper::toDto).toList();
    }

    @Transactional
    @Override
    public List<ScheduleDTO> getScheduleByDoctor(Long id) {
        if (id == null) throw new IllegalArgumentException("Schedule or Id not valid");
        List<ScheduleEntity> schedule = scheduleRepository.findAllActiveSchedulesByDoctorId(id);
        return schedule.stream().map(ScheduleMapper::toDto).toList();
    }

    @Transactional
    @Override
    public ScheduleDTO getScheduleById(Long id) {
        if (id == null) throw new IllegalArgumentException("Schedule or Id not valid");
        ScheduleEntity schedule = scheduleRepository.findById(id).orElseThrow(()-> new NotFoundException("Schedule Not Found"));
        return ScheduleMapper.toDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleDTO createSchedule(ScheduleDTO schedule) {
        if (schedule == null || schedule.getIdDoctor() == null) throw new IllegalArgumentException("Schedule or Id not valid");
        DoctorEntity doctor = doctorRepository.findById(schedule.getIdDoctor()).orElseThrow(()-> new NotFoundException("Schedule Not Found"));
        ScheduleEntity created = ScheduleMapper.toEntity(schedule,doctor);

        return ScheduleMapper.toDto(scheduleRepository.save(created));
    }

    @Transactional
    @Override
    public ScheduleDTO updateSchedule(Long id, ScheduleDTO schedule) {
        if (schedule == null || schedule.getIdDoctor() == null || id == null) throw new IllegalArgumentException("Schedule or Id not valid");
        ScheduleEntity founded = scheduleRepository.findById(id).orElseThrow(()-> new NotFoundException("Schedule Not Found"));
        founded.setDayOfWeek(schedule.getDayOfWeek());
        founded.setInitDate(schedule.getInitDate());
        founded.setEndDate(schedule.getEndDate());
        return Mapper.toDto(scheduleRepository.save(founded));

    }

    @Override
    public void deleteSchedule(Long id) {
        if (id != null) {
            ScheduleEntity founded = scheduleRepository.findById(id).orElseThrow(()-> new NotFoundException("Schedule Not Found"));
            founded.setIsActive(false);
            scheduleRepository.save(founded);
        }else throw new IllegalArgumentException("Schedule or Id not valid");


    }
}
