package com.ccc.quotedemo.service.patient;

import com.ccc.quotedemo.dto.PatientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {

    Page<PatientDTO> getPatients(Pageable pageable);
    PatientDTO getPatientById(Long id);
    PatientDTO createPatient(PatientDTO patient);
    PatientDTO updatePatient(Long id,PatientDTO patient);
    void deletePatient(Long id);
}
