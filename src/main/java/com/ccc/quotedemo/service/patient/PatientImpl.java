package com.ccc.quotedemo.service.patient;

import com.ccc.quotedemo.dto.PatientDTO;
import com.ccc.quotedemo.exception.NotFoundException;
import com.ccc.quotedemo.mapper.Mapper;
import com.ccc.quotedemo.model.PatientEntity;
import com.ccc.quotedemo.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientImpl implements PatientService{

    @Autowired
    PatientRepository patientRepository;


    @Transactional
    @Override
    public Page<PatientDTO> getPatients(Pageable pageable) {
        Page<PatientEntity> patient = patientRepository.findByIsActiveTrue(pageable);
        return patient.map(Mapper::toDto);
    }

    @Transactional
    @Override
    public PatientDTO getPatientById(Long id) {
        if (id == null) return null;
        PatientEntity patient = patientRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient Not Found"));

        return Mapper.toDto(patient);
    }

    @Transactional
    @Override
    public PatientDTO createPatient(PatientDTO patient) {
        if (patient == null) return null;
        PatientEntity patientEntity = Mapper.toEntity(patient);

        PatientEntity savedEntity = patientRepository.save(patientEntity);
        return Mapper.toDto(savedEntity);
    }

    @Transactional
    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patient) {
        if (id == null) return null;
        PatientEntity pat = patientRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient Not Found"));
        pat.setName(patient.getName());
        pat.setAge(patient.getAge());
        pat.setPhoneNumber(patient.getPhoneNumber());
        PatientEntity savedPatient = patientRepository.save(pat);
        return Mapper.toDto(savedPatient);
    }

    @Transactional
    @Override
    public void deletePatient(Long id) {
        PatientEntity pat = patientRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient Not Found"));
        pat.setIsActive(false);
        patientRepository.save(pat);

    }
}
