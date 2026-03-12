package com.ccc.quotedemo.service.doctor;

import com.ccc.quotedemo.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {

    List<DoctorDTO> getDoctors();
    DoctorDTO getDoctorById(Long id);
    DoctorDTO linkDoctorWithServices(Long idDoctor, Long idServices);
    DoctorDTO unlinkDoctorWithServices(Long idDoctor, Long idServices);
    DoctorDTO createDoctors(DoctorDTO doctorDto);
    DoctorDTO updateDoctors(Long id,DoctorDTO doctorDto);
    void deleteDoctor(Long id);
}
