package com.ccc.quotedemo.controller;

import com.ccc.quotedemo.dto.PatientDTO;
import com.ccc.quotedemo.service.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<Page<PatientDTO>> getPatients(Pageable pageable){
        return ResponseEntity.ok(patientService.getPatients(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatients(@RequestBody PatientDTO patientDTO){
        PatientDTO pat = patientService.createPatient(patientDTO);
        return ResponseEntity.created(URI.create("api/v1/patient" + pat.getIdPatient())).body(pat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO, @PathVariable Long id){
        return ResponseEntity.ok(patientService.updatePatient(id,patientDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
