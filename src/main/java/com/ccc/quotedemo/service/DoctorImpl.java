package com.ccc.quotedemo.service;

import com.ccc.quotedemo.dto.DoctorDTO;
import com.ccc.quotedemo.exception.NotFoundException;
import com.ccc.quotedemo.mapper.Mapper;
import com.ccc.quotedemo.model.DoctorEntity;
import com.ccc.quotedemo.repository.DoctorRepository;
import com.ccc.quotedemo.repository.ScheduleBlockoutRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorImpl implements DoctorService{

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ScheduleBlockoutRepository scheduleBlockOutRepository;


    @Transactional
    @Override
    public List<DoctorDTO> getDoctors() {
        List<DoctorEntity> doctor = doctorRepository.findByIsActiveTrue();

        Set<Long> blockedIds = scheduleBlockOutRepository.findAll().stream().map(b -> b.getDoctor().getIdDoctor()).collect(Collectors.toSet());

        return doctor.stream().map(doc -> Mapper.toSummaryDto(doc,!blockedIds.contains(doc.getIdDoctor()))).toList();
    }

    @Transactional
    @Override
    public DoctorDTO getDoctorById(Long id) {
        DoctorEntity doctorEntity = doctorRepository.findByIdWithSchedules(id).orElseThrow(() -> new NotFoundException("Doctor Not Found"));
        Boolean isAvailable = scheduleBlockOutRepository.existsByDoctor_IdDoctor(id);

        return Mapper.toDto(doctorEntity, isAvailable);
    }

    @Transactional
    @Override
    public DoctorDTO createDoctors(DoctorDTO doctorDto) {
        if (doctorDto == null) return null;

        DoctorEntity doctorEntity = Mapper.toEntity(doctorDto);

        DoctorEntity savedEntity = doctorRepository.save(doctorEntity);

        return Mapper.toDto(savedEntity,true);
    }

    @Transactional
    @Override
    public DoctorDTO updateDoctors(Long id, DoctorDTO doctorDto) {
        if (doctorDto == null) return null;
        DoctorEntity foundedDoc = doctorRepository.findById(id).orElseThrow(() -> new NotFoundException("Doctor Not Found"));

        foundedDoc.setName(doctorDto.getName());
        foundedDoc.setTitle(doctorDto.getTitle());

        DoctorEntity savedEntity = doctorRepository.save(foundedDoc);
        Boolean isAvailable = scheduleBlockOutRepository.existsByDoctor_IdDoctor(id);


        return Mapper.toDto(savedEntity, isAvailable);
    }
    @Transactional
    @Override
    public void deleteDoctor(Long id) {
        DoctorEntity foundedDoc = doctorRepository.findById(id).orElseThrow(() -> new NotFoundException("Doctor Not Found"));
        foundedDoc.setIsActive(false);
        doctorRepository.save(foundedDoc);
    }
}
