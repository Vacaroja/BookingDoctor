package com.ccc.quotedemo.service;

import com.ccc.quotedemo.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {

    List<DoctorDTO> getDoctors();
    DoctorDTO getDoctorById(Long id);
    DoctorDTO createDoctors(DoctorDTO doctorDto);
    DoctorDTO updateDoctors(Long id,DoctorDTO doctorDto);
    void deleteDoctor(Long id);
}
